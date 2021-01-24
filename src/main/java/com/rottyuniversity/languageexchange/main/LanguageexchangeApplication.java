package com.rottyuniversity.languageexchange.main;

import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan("com.rottyuniversity.languageexchange")
public class LanguageexchangeApplication {

	public static void main(String[] args) {

		SpringApplication.run(LanguageexchangeApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "database");
	}
}
