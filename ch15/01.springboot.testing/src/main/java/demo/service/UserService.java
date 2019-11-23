package demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import demo.entity.User;

@Service
public class UserService {

	public List<User> getAllUsers() {

		User user1 = new User(1, "name1", "surname1", "password");
		User user2 = new User(2, "name2", "surname2", "password");
		User user3 = new User(3, "name3", "surname3", "password");

		return Arrays.asList(user1, user2, user3);

	}
}
