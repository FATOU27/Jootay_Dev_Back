package com.fd.Jootay_Dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class JootayDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(JootayDevApplication.class, args);
	}

}
