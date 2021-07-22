package com.higroup.Buda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudaApplication.class, args);
	}

}
