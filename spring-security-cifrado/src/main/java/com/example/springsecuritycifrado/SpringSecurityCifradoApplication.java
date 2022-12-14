package com.example.springsecuritycifrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityCifradoApplication {

	@Bean
	public PasswordEncoder passwordEncoder (){
		return  new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringSecurityCifradoApplication.class, args);
		UserRepository repo  = context.getBean(UserRepository.class);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		User user = new User(null,"bryan","admin");
		User userCifrado = new User(null,"raquel",encoder.encode("admin"));

		repo.save(user);
		repo.save(userCifrado);

	}

}
