package demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.User;
import demo.service.UserService;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = Application.class)
@SpringBootTest

/*
 * Spring Boot provides the @SpringBootTest annotation, which uses
 * SpringApplication behind the scenes to load ApplicationContext so that all
 * the Spring Boot features will be available
 * 
 */
public class UserServiceTestV2 {

	@Autowired
	UserService userService;

	@Test
	public void shoul_load_all_users() {
		List<User> users = userService.getAllUsers();

		assertNotNull(users);
		assertEquals(3, users.size());
	}
}
