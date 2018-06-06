package com.thiyagu.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Entry point for todo application
 * 
 * @author Thiyagu
 *
 */
@SpringBootApplication
@CrossOrigin
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}
}
