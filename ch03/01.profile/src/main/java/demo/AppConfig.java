package demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

	@Bean
	@Profile("DEV")
	public DataSource devDataSource() {
		System.out.println("dev");
		///....
		return null;
	}
	
	@Bean
	@Profile("PROD")
	public String prodDataSource() {
		System.out.println("prod environment");
		///....
		return null;
	}
}
