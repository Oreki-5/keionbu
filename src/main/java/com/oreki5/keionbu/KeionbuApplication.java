package com.oreki5.keionbu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeionbuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeionbuApplication.class, args);
	}

}
