package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * We haven’t defined any of the DataSource, EntityManagerFactory, or
	 * TransactionManager beans, but they are automatically created. How?
	 */

	/*
	 * If you have any in-memory database drivers like H2 or HSQL in the classpath,
	 * then Spring Boot will automatically create an in-memory datasource and will
	 * register the EntityManagerFactory and TransactionManager beans automatically
	 * with sensible defaults.
	 */

	/*
	 * But you are using MySQL, so you need to explicitly provide MySQL connection
	 * details. You have configured those MySQL connection details in the
	 * application.properties file and Spring Boot creates a DataSource using those
	 * properties.
	 */

	// Embedded Servlet Container Support
	/*
	 * The most important and surprising thing is that we created a simple Java
	 * class annotated with some magical annotation (@SpringApplication), which has
	 * a main() method. By running that main() method, we are able to run the
	 * application and access it at http://localhost:8080/. Where does the servlet
	 * container come from?
	 */

	/*
	 * 
	 * We added spring-boot-starter-web, which pulls spring-boot-starter-tomcat
	 * automatically. When we run the main() method, it starts tomcat as an embedded
	 * container so that we don’t have to deploy our application on any externally
	 * installed tomcat server. What if we want to use a Jetty server instead of
	 * Tomcat? You simply exclude spring-boot-starter-tomcat from
	 * spring-boot-starter-web and include spring- boot-starter-jetty
	 */
}
