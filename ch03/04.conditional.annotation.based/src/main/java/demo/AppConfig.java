package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import condition.DatabaseType;
import dao.JdbcUserDAO;
import dao.MongoUserDAO;
import dao.UserDAO;

@Configuration
public class AppConfig {

	@Bean
	@DatabaseType("MYSQL")
	public UserDAO jdbcUserDAO() {
		System.out.println("jdbcUserDAO");
		return new JdbcUserDAO();
	}

	@Bean
	@DatabaseType("MONGODB")
	public UserDAO mongoUserDAO() {
		System.out.println("mongoUserDAO");
		return new MongoUserDAO();
	}
}