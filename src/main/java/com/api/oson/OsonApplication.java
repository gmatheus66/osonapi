package com.api.oson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
@MultipartConfig
public class OsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsonApplication.class, args);
	}

}
