package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// What if you want to register the MongoUserDAO bean only if the
		// dbType=MONGO property is set in the property’s placeholder configuration
		// file?

		// Instead of creating a condition implementation for MYSQL and MongoDB, you can
		// create a condition.DatabaseType annotation

		// Then you implement DatabaseTypeCondition to use the DatabaseType value to
		// determine whether to enable or disable bean registration ;
		// condition.DatabaseTypeCondition
		
		// you can use the @DatabaseType annotation on the bean definitions; demo.AppConfig
	}
}
