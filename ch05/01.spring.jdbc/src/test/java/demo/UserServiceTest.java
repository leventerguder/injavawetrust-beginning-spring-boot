package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class UserServiceTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAllUsers() {
		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void findUserById() {
		User user = userRepository.findUserById(1);
		assertNotNull(user);
	}

	@Test
	public void createUser() {
		User user = new User(0, "cool-user", "cool-user@injavawetrust.com");
		User savedUser = userRepository.create(user);
		User newUser = userRepository.findUserById(savedUser.getId());
		assertEquals("cool-user", newUser.getName());
		assertEquals("cool-user@injavawetrust.com", newUser.getEmail());
	}
}
