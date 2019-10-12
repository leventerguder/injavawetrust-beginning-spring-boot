package demo.info;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ExampleInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("example",
				Collections.singletonMap("key", "value"));
	}
	// Writing Custom InfoContributors
	// To provide custom application information, you can register Spring beans that
	// implement the InfoContributor interface.

	// https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
	
	// http://localhost:8080/actuator/info
}