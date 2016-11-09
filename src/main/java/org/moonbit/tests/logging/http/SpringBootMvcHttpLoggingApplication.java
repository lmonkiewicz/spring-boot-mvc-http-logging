package org.moonbit.tests.logging.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class SpringBootMvcHttpLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcHttpLoggingApplication.class, args);
	}
}
