package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.entity.User;
import demo.entity.UsersImportResponse;
import demo.exception.UserImportServiceCommunicationFailure;

public class UsersImportService {

	private UsersImporter usersImporter;

	@Autowired
	public UsersImportService(UsersImporter usersImporter) {
		this.usersImporter = usersImporter;
	}

	public UsersImportResponse importUsers() {
		int retryCount = 0;
		int maxRetryCount = 3;
		for (int i = 0; i < maxRetryCount; i++) {
			try {
				List<User> importUsers = usersImporter.importUsers();
			} catch (UserImportServiceCommunicationFailure e) {
				retryCount++;
			}
		}
		if (retryCount >= maxRetryCount)
			return new UsersImportResponse(retryCount, "FAILURE");
		else
			return new UsersImportResponse(0, "SUCCESS");
	}
}
