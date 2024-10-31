package cantina.project.cantinaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "cantina.project.cantinaProject", exclude = {
		DataSourceAutoConfiguration.class })
public class CantinaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CantinaProjectApplication.class, args);
	}

}
