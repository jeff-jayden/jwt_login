package com.example.jwt_login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.example.jwt_login.dao")
@CrossOrigin
public class JwtLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtLoginApplication.class, args);
	}

}
