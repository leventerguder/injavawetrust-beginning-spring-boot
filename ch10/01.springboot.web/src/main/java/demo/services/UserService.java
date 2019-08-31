package demo.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private String message = "This is a test message";

	public String getMessage() {
		return message;
	}
}
