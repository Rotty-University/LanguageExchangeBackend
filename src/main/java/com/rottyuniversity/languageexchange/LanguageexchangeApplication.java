package com.rottyuniversity.languageexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LanguageexchangeApplication {

	public static void main(String[] args) {

		SpringApplication.run(LanguageexchangeApplication.class, args);

		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String p = pe.encode("hello");
		System.out.println(p);
		System.out.println(pe.matches("hello", p));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
