/*
 * Copyright (c) 2019.
 * Developer: Hamid Atyabi
 * Email: atyabi.hamid@yahoo.com
 * Website: www.atyabi.com
 */

package com.microservice.auth;

import com.microservice.auth.common.repository.ClientDao;
import com.microservice.auth.common.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class AuthApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			LOGGER.info("Spring boot loaded successfully.");
			LOGGER.info("User: "+userDao.getUserByUserName("admin"));
			LOGGER.info("Client: "+clientDao.loadClientByClientId("client"));
		};
	}
}
