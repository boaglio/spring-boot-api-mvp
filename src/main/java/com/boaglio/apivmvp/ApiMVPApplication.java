package com.boaglio.apivmvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.boaglio")
public class ApiMVPApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMVPApplication.class, args);
	}

}