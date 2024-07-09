package com.teamdevroute.devroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevRouteApplication.class, args);
	}

}
