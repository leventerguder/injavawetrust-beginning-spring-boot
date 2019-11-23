package demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.exception.UserImportServiceCommunicationFailure;

@Service
public class UsersImporter {
	public List<User> importUsers() throws UserImportServiceCommunicationFailure {
		List<User> users = new ArrayList<>();
		// get users by invoking some web service
		// if any exception occurs throw UserImportServiceCommunicationFailure
		// dummy data
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
}