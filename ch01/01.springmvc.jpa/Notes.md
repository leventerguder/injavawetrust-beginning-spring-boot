Spring at its core is a dependency injection container that provides flexibility to configure beans in multiple ways, such as XML, Annotations, and JavaConfig.

The Spring framework is very flexible and provides multiple ways of configuring the application components.


# Overview of the Spring Framework

- Spring's dependency injection approach encourages writing testable code.
- Easy-to-use and powerful database transaction management capabilities
- Spring simplifies integration with other Java frameworks , like the JPA/Hibernate ORM and Structs/JSF web frameworks

There are many othet Spring sub-projects that help build applications that address modern business needs :

- Spring Data: Simplifies data access from relational and NoSQL datastores.
- Spring Batch: Provides a powerful batch-processing framework.
- Spring Security: Robust security framework to secure applications.
- Spring Social: Supports integration with social networking sites like Facebook,Twitter, LinkedIn, GitHub, etc.
- Spring Integration: An implementation of enterprise integration patterns to facilitate integration with other enterprise applications using lightweight messaging and declarative adapters.

https://spring.io/projects

# Spring Configuration Styles

Spring initially provided an XML-based approach for configuring beans. Later Spring introduced XML-based DSLs, Annotations, and JavaConfig-based approaches for configuring beans.

Spring provides multiple approaches for configuring application components and
you can even mix the approaches as well. For example, you can use JavaConfig- and Annotation-based configuration styles in the same application.

# Developing Web Application Using SpringMVC and JPA

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="demo.repositories")
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig


- Marked it as a Spring Configuration class using the @Configuration annotation.
- Enabled Annotation-based transaction management using @EnableTransactionManagement
- Configure @EnableJpaRepositories to indicate where to look for Spring Data JPA repositories.
- Configured the PropertyPlaceHolder bean using the @PropertySource annotation and PropertySourcesPlaceholderConfigurer bean definition, which loads properties from the application.properties file.
- Defined beans for DataSource, JPA EntityManagerFactory, and JpaTransactionManager.


@Configuration
@ComponentScan(basePackages = { "demo.web"})
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

- Marked it as a Spring Configuration class using @Configuration annotation.
- Enabled Annotation-based Spring MVC configuration using @EnableWebMvc.
- Configured ThymeleafViewResolver by registering the TemplateResolver, SpringTemplateEngine, and ThymeleafViewResolver beans.
