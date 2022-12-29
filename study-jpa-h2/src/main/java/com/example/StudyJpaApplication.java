package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class StudyJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyJpaApplication.class, args);
	}

}
