package demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("demo.mappers")
// @MapperScan annotation to specify where to look for the mapper interfaces
public class SpringBootMyBatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMyBatisDemoApplication.class, args);
	}

}
