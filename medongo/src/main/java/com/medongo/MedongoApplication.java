package com.medongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedongoApplication {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(MedongoApplication.class, args);
	}

}
