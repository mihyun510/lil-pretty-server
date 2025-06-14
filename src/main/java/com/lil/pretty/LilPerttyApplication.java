package com.lil.pretty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LilPerttyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LilPerttyApplication.class, args);
	}

}
