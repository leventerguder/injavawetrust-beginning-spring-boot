package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.SpringBootMultipleDSDemoApplication;
import demo.orders.entities.Order;
import demo.orders.repositories.OrderRepository;
import demo.security.entities.User;
import demo.security.repositories.UserRepository;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMultipleDSDemoApplication.class)
public class SpringbootMultipleDSDemoApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void findAllUsers() {
		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());

	}

	@Test
	public void findAllOrders() {
		List<Order> orders = orderRepository.findAll();
		assertNotNull(orders);
		assertTrue(!orders.isEmpty());

	}

}
