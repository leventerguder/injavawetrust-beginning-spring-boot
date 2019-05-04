package configuration;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.ClassUtils;

@Configuration

// Class conditions allow us to specify that a configuration bean will be
// included if a specified class is present using the @ConditionalOnClass
// annotation, or if a class is absent using the @ConditionalOnMissingClass
// annotation.

// Let’s specify that our MySQLConfiguration will only be loaded if the class
// DataSource is present, in which case we can assume the application will use a
// database:
@ConditionalOnClass(DataSource.class)

//If we want our auto-configuration class to have priority over other
//auto-configuration candidates, we can add the
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE) annotation.
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)

// The @ConditionalOnProperty annotation is used to specify if a configuration
// will be loaded based on the presence and value of a Spring Environment
// property.

// First, let’s add a property source file for our configuration that will
// determine where the properties will be read from:
@PropertySource("classpath:mysql.properties")

public class MySQLAutoConfiguration {
	@Autowired
	private Environment env;

	// The @ConditionalOnProperty annotation is used to specify if a configuration
	// will be loaded based on the presence and value of a Spring Environment
	// property.

	// We can use the attribute havingValue to specify certain values of the
	// usemysql property that have to be matched.

	// The mysql.properties file will contain the usemysql property:
	// usemysql=dev

	@Bean
	@ConditionalOnProperty(name = "usemysql", havingValue = "dev")
	@ConditionalOnMissingBean
	public DataSource dataSource() {

		System.out.println("dataSource#local");
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_conditions?createDatabaseIfNotExist=true&&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("12345678");

		return dataSource;
	}

	@Bean(name = "dataSource")
	@ConditionalOnProperty(name = "usemysql", havingValue = "prod")
	@ConditionalOnMissingBean
	public DataSource dataSource2() {

		System.out.println("dataSource#prod");

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(env.getProperty("mysql.url"));
		dataSource.setUsername(env.getProperty("mysql.user") != null ? env.getProperty("mysql.user") : "");
		dataSource.setPassword(env.getProperty("mysql.pass") != null ? env.getProperty("mysql.pass") : "");

		return dataSource;
	}

	// If we want to include a bean only if a specified bean is present or not, we
	// can use the @ConditionalOnBean and @ConditionalOnMissingBean annotations.

	// To exemplify this, let’s add an entityManagerFactory bean to our
	// configuration class, and specify we only want this bean to be created if a
	// bean called dataSource is present and if a bean called entityManagerFactory
	// is not already defined:

	@Bean
	@ConditionalOnBean(name = "dataSource")
	@ConditionalOnMissingBean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("demo");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		if (additionalProperties() != null) {
			em.setJpaProperties(additionalProperties());
		}
		return em;
	}

	// Let’s also configure a transactionManager bean that will only be loaded if a
	// bean of type JpaTransactionManager is not already defined:
	@Bean
	@ConditionalOnMissingBean(type = "JpaTransactionManager")
	JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	// Adding the @ConditionalOnResource annotation means that the configuration
	// will only be loaded when a specified resource is present.

	// Let’s define a method called additionalProperties() that will return a
	// Properties object containing Hibernate-specific properties to be used by the
	// entityManagerFactory bean, only if the resource file mysql.properties is
	// present;
	
	@ConditionalOnResource(resources = "classpath:mysql.properties")
	@Conditional(HibernateCondition.class)
	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("mysql-hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("mysql-hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql",
				env.getProperty("mysql-hibernate.show_sql") != null ? env.getProperty("mysql-hibernate.show_sql")
						: "false");

		return hibernateProperties;
	}

	// If we don’t want to use any of the conditions available in Spring Boot, we
	// can also define custom conditions by extending the SpringBootCondition class
	// and overriding the getMatchOutcome() method.

	// Let’s create a condition called HibernateCondition for our
	// additionalProperties() method that will verify whether a
	// HibernateEntityManager class is present on the classpath:
	
	static class HibernateCondition extends SpringBootCondition {

		private static final String[] CLASS_NAMES = { "org.hibernate.ejb.HibernateEntityManager",
				"org.hibernate.jpa.HibernateEntityManager" };

		@Override
		public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
			ConditionMessage.Builder message = ConditionMessage.forCondition("Hibernate");

			return Arrays.stream(CLASS_NAMES)
					.filter(className -> ClassUtils.isPresent(className, context.getClassLoader()))
					.map(className -> ConditionOutcome.match(message.found("class").items(Style.NORMAL, className)))
					.findAny().orElseGet(() -> ConditionOutcome.noMatch(
							message.didNotFind("class", "classes").items(Style.NORMAL, Arrays.asList(CLASS_NAMES))));
		}

	}
}
