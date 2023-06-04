package com.api.pay2you;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.api.pay2you")
public class Pay2youApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pay2youApplication.class, args);
	}

}
