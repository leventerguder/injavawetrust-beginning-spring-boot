package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		
		// http://localhost:8080/actuator
		// https://www.callicoder.com/spring-boot-actuator/
	}
}
