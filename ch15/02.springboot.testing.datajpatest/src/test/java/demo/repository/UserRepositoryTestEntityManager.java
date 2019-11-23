package demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTestEntityManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindByEmail() {
		User user = new User("john", "john@gmail.com", "password");
		Integer id = entityManager.persistAndGetId(user, Integer.class);
		User john = userRepository.findByEmail("john@gmail.com");
		assertNotNull(john);
		assertEquals(id, john.getId());
		assertEquals("john", user.getName());
	}
}
