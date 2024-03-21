package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderBookApplication.class, args);
		System.out.println("application started successfully");
	}

}
