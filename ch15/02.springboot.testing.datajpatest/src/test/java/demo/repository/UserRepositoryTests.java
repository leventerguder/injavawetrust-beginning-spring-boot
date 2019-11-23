package demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindUserByEmail() throws Exception {
		User user = this.userRepository.findByEmail("admin@gmail.com");
		assertThat(user.getEmail()).isEqualTo("admin@gmail.com");
		assertThat(user.getPassword()).isEqualTo("admin");
	}

	/*
	 * The @DataJpaTest tests are transactional and rolled back at the end of each
	 * test by default. You can disable this default rollback behavior for a single
	 * test or for an entire test class by annotating
	 * with @Transactional(propagation = Propagation.NOT_SUPPORTED)
	 * 
	 */

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void testCreateUser() {
		User user = new User("john", "john@gmail.com", "password");
		userRepository.save(user);
		// assertions
	}

	@Test
	public void testUpdateUser() {
		User user = userRepository.findByEmail("admin@gmail.com");
		user.setName("Administrator");
		userRepository.save(user);
		// assertions
	}

}
