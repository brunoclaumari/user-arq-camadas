package com.layer_arch.userdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdemoApplication.class, args);
	}

}
