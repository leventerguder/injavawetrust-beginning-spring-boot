package demo.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration

/*
 * you enabled Spring Data JPA support by using the @EnableJpaRepositories
 * annotation. As you are going to have multiple EntityManagerFactory and
 * TransactionManager beans, you configured the bean IDs for
 * entityManagerFactoryRef and transactionManagerRef by pointing to the
 * respective bean names. You also configured the basePackages property to
 * indicate where to look for the Spring Data JPA repositories
 */
@EnableJpaRepositories(basePackages = "demo.orders.repositories", entityManagerFactoryRef = "ordersEntityManagerFactory", transactionManagerRef = "ordersTransactionManager")
public class OrdersDBConfig {

	@Autowired
	private Environment env;

	/*
	 * Observe that you have populated the datasource.security.* properties into
	 * DataSourceProperties by
	 * using @ConfigurationProperties(prefix="datasource.security") and
	 * DataSourceBuilder fluent API to create the DataSource bean.
	 */
	@Bean
	@ConfigurationProperties(prefix = "datasource.orders")
	public DataSourceProperties ordersDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource ordersDataSource() {
		DataSourceProperties primaryDataSourceProperties = ordersDataSourceProperties();
		return DataSourceBuilder.create().driverClassName(primaryDataSourceProperties.getDriverClassName())
				.url(primaryDataSourceProperties.getUrl()).username(primaryDataSourceProperties.getUsername())
				.password(primaryDataSourceProperties.getPassword()).build();
	}

	@Bean
	public PlatformTransactionManager ordersTransactionManager() {
		EntityManagerFactory factory = ordersEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	/*
	 * While creating the LocalContainerEntityManagerFactoryBean bean, you have
	 * configured the package called demo.orders.entities to scan for JPA entities.
	 * 
	 */

	@Bean
	public LocalContainerEntityManagerFactoryBean ordersEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(ordersDataSource());
		factory.setPackagesToScan("demo.orders.entities");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);

		return factory;

	}

	/*
	 * You have configured the DataSourceInitializer bean to initialize the sample
	 * data from orders-data.sql
	 */

	@Bean
	public DataSourceInitializer ordersDataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(ordersDataSource());
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("orders-data.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(env.getProperty("datasource.orders.initialize", Boolean.class, false));
		return dataSourceInitializer;
	}

}
