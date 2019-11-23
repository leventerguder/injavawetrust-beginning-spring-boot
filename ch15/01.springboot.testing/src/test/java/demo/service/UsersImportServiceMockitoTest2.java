package demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.UsersImportResponse;
import demo.exception.UserImportServiceCommunicationFailure;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersImportServiceMockitoTest2 {

	@MockBean
	private UsersImporter usersImporter;
	
	@Autowired
	private UsersImportService usersImportService;

	@Test
    public void should_retry_3times_when_UserImportServiceCommunicationFailure_occured()
    {
        given(usersImporter.importUsers()).willThrow(new UserImportServiceCommunicationFailure());
        UsersImportResponse response = usersImportService.importUsers();
        assertThat(response.getRetryCount()).isEqualTo(3);
        assertThat(response.getStatus()).isEqualTo("FAILURE");
    }
}