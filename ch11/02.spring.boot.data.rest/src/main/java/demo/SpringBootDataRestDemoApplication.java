package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataRestDemoApplication {

	
	// http://localhost:8080/api/users
	// http://localhost:8080/api/posts
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataRestDemoApplication.class, args);
	}

}
