package com.yangworld.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SpringServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

}
