package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// While developing Spring-based applications , you may come across aneed to
	// register beans conditionally.
	// For example, you may want to register a DataSource bean pointing to the DEV
	// database when running applications locally and point to a different
	// PRODUCTION database when running in production.

	// You can register multiple beans of the same type and associate them with one
	// or more profiles. When you run the application, you can activate the desired
	// profile(s).

	// With this configuration, you can specify the active profile using the
	// -Dspring.profiles.active=DEV system property.
	
	// Hence, devDataSource method is invoked.
}
