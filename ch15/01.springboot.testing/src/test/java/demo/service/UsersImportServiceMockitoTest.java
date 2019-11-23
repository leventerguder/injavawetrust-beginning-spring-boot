package demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import demo.entity.UsersImportResponse;
import demo.exception.UserImportServiceCommunicationFailure;

@RunWith(MockitoJUnitRunner.class)
public class UsersImportServiceMockitoTest {

	/*
	 * You can use @Mock to create a mock object and @InjectMocks to inject the
	 * dependencies with mocks. You can use @RunWith(MockitoJUnitRunner.class) to
	 * initialize the mock objects or trigger the mock object initialization using
	 * MockitoAnnotations.initMocks(this) in the JUnit @Before method.
	 */
	
	@Mock
	private UsersImporter usersImporter;

	@InjectMocks
	private UsersImportService usersImportService;

	@Test
	public void should_retry_3times_when_UserImportServiceCommunicationFailure_occured() {

		given(usersImporter.importUsers()).willThrow(new UserImportServiceCommunicationFailure());
		UsersImportResponse response = usersImportService.importUsers();

		assertThat(response.getRetryCount()).isEqualTo(3);
		assertThat(response.getStatus()).isEqualTo("FAILURE");
	}
}