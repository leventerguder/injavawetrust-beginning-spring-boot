package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class Application {

	public static void main(String[] args) {

		/*
		 * You are bootstrapping the application by calling SpringApplication.
		 * run(SpringbootBasicApplication.class, args) in the main() method. You can
		 * pass one or more Spring configuration classes the SpringApplication. run()
		 * method.
		 */
		SpringApplication.run(Application.class, args);
	}

	// Spring Boot is an opinionated framework that helps developers build
	// Spring-based applications quickly and easily

	// Spring Boot offers many starter modules to get started quickly with many of
	// the commonly used technologies, like SpringMVC, JPA, MongoDB, Spring Batch,
	// SpringSecurity, Solr, ElasticSearch, etc.

	// These starters are pre-configured with the most commonly used library
	// dependencies so you don’t have to search for the compatible library versions
	// and configure them manually.

	// Spring Boot addresses the problem that Spring applications need complex
	// configuration by eliminating the need to manually set up the boilerplate
	// configuration.

	// if you have the spring-webmvc dependency in your classpath, Spring Boot
	// assumes you are trying to build a SpringMVC-based web application and
	// automatically tries to register DispatcherServlet if it is not already
	// registered.

	// If you have any embedded database drivers in the classpath, such as H2 or
	// HSQL, and if you haven’t configured a DataSource bean explicitly, then Spring
	// Boot will automatically register a DataSource bean using in-memory database
	// settings

	/*
	 * @Target(ElementType.TYPE)
	 * 
	 * @Retention(RetentionPolicy.RUNTIME)
	 * 
	 * @Documented
	 * 
	 * @Inherited
	 * 
	 * @SpringBootConfiguration
	 * 
	 * @EnableAutoConfiguration
	 * 
	 * @ComponentScan(excludeFilters = {
	 * 
	 * @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
	 * 
	 * @Filter(type = FilterType.CUSTOM, classes =
	 * AutoConfigurationExcludeFilter.class) }) public @interface
	 * SpringBootApplication {
	 */

	// @Configuration indicates that this class is a Spring configuration class.
	// @ComponentScan enables component scanning for Spring beans in the package in
	// which the current class is defined.
	// @EnableAutoConfiguration triggers Spring Boot’s autoconfiguration mechanisms.
}
