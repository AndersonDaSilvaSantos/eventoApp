package com.reunioesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ReunioesappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReunioesappApplication.class, args);
		
	}
}