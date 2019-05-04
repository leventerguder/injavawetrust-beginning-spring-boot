package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import condition.MongoDriverNotPresentsCondition;
import condition.MongoDriverPresentsCondition;
import dao.JdbcUserDAO;
import dao.MongoUserDAO;
import dao.UserDAO;

@Configuration
public class AppConfig {

	@Bean
	@Conditional(MongoDriverNotPresentsCondition.class)
	public UserDAO jdbcUserDAO() {
		System.out.println("jdbcUserDAO");
		return new JdbcUserDAO();
	}

	@Bean
	@Conditional(MongoDriverPresentsCondition.class)
	public UserDAO mongoUserDAO() {
		System.out.println("mongoUserDAO");
		return new MongoUserDAO();
	}
}