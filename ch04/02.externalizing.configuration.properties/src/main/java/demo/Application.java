package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private DataSourceConfig dataSourceConfig;

	@Autowired
	private DataSourceConfigV2 dataSourceConfigV2;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(dataSourceConfig);
		System.out.println(dataSourceConfigV2);
		
	}

}
