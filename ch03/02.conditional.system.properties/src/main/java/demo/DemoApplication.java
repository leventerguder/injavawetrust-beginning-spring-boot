package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Suppose you have a UserDAO interface with methods to get data from a
	// datastore. You have two implementations of UserDAO interface—JdbcUserDAO
	// talks to the MySQL database and MongoUserDAO talks to MongoDB. You may want
	// to enable only JdbcUserDAO or MongoUserDAO based on a specific system
	// property, say dbType.

	// if you set the system property to -DdbType=MONGODB, the MongoUserDAO bean
	// will be registered.
}
