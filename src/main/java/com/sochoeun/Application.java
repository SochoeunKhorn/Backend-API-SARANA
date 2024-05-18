package com.sochoeun;

import com.sochoeun.security.user.Role;
import com.sochoeun.security.user.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(
			RoleRepository roleRepository
	) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
			if (roleRepository.findByName("ADMIN").isEmpty()) {
				roleRepository.save(Role.builder().name("ADMIN").build());
			}
			if (roleRepository.findByName("MANAGEMENT").isEmpty()) {
				roleRepository.save(Role.builder().name("MANAGEMENT").build());
			}

		};
	}*/
}
