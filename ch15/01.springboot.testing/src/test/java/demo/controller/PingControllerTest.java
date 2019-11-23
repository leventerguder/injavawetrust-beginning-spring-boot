package demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerTest {

	private TestRestTemplate restTemplate;

	@Test
	public void testPing() {

		ResponseEntity<String> respEntity = restTemplate.getForEntity("/ping", String.class);

		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(respEntity.getBody()).isEqualTo("OK");

	}
}
