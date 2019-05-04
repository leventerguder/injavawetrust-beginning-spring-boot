package dao;

import java.util.List;
import java.util.Arrays;

public class MongoUserDAO implements UserDAO {
	@Override
	public List<String> getAllUserNames() {
		System.out.println("**** Getting usernames from MongoDB *****");
		return Arrays.asList("Bond", "James", "Bond");
	}
}