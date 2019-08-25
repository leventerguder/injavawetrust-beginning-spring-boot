
package demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.orders.entities.Order;
import demo.orders.repositories.OrderRepository;
import demo.security.entities.User;
import demo.security.repositories.UserRepository;

@Service
public class UserOrdersService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(transactionManager = "securityTransactionManager")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional(transactionManager = "ordersTransactionManager")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
}
