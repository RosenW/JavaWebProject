package project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project.services.implementations.MemeServiceImpl;
import project.services.interfaces.MemeService;

@SpringBootApplication
public class SiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
	@Bean
	public ModelMapper createMapper() {
		return new ModelMapper();
	}
}
