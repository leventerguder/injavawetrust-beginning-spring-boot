package dao;

import java.util.List;
import java.util.Arrays;

public class JdbcUserDAO implements UserDAO {
	@Override
	public List<String> getAllUserNames() {
		System.out.println("**** Getting usernames from RDBMS *****");
		return Arrays.asList("Jim", "John", "Rob");
	}
}
