package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import condition.MySQLDatabaseTypeCondition;
import condition.MongoDBDatabaseTypeCondition;
import dao.JdbcUserDAO;
import dao.MongoUserDAO;
import dao.UserDAO;

@Configuration
public class AppConfig {

	// you can configure both the JdbcUserDAO and MongoUserDAO beans conditionally
	// using @ Conditional
	@Bean
	@Conditional(MySQLDatabaseTypeCondition.class)
	public UserDAO jdbcUserDAO() {
		System.out.println("jdbcUserDAO");
		return new JdbcUserDAO();
	}

	@Bean
	@Conditional(MongoDBDatabaseTypeCondition.class)
	public UserDAO mongoUserDAO() {
		System.out.println("mongoUserDAO");
		return new MongoUserDAO();
	}
}