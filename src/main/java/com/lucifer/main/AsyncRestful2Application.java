package com.trend.frs.lucifer.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.trend.frs.lucifer")
public class AsyncRestful2Application {
	public static void main(String[] args) {
		SpringApplication.run(AsyncRestful2Application.class, args);
	}
}
