package com.transport.karenstransportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.transport")
public class KarenskidstransportationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarenskidstransportationApplication.class, args);
	}

}
