package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Suppose you want to register the MongoUserDAO bean only when the MongoDB Java
	// driver class called com. mongodb.Server is available on the classpath.
	// Otherwise, you want to register the JdbcUserDAO bean.
	// To accomplish this, you can create conditions to check the presence or
	// absence of the MongoDB driver class called com.mongodb.Server,
}
