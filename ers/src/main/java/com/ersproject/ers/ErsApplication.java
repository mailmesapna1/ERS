package com.ersproject.ers;

import com.ersproject.ers.model.Role;
import com.ersproject.ers.model.User;
import com.ersproject.ers.repository.RoleRepository;
import com.ersproject.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class ErsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
		return args -> {
			// Check if the ADMIN role exists
			Role adminRole = roleRepository.findByAuthority("ADMIN");
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setAuthority("ADMIN");
				roleRepository.save(adminRole);
			}

			// Check if the user with username "admin" exists
			Optional<User> userOptional  = userRepository.findByUsername("9876543210");
			if (userOptional.isEmpty()) {
				User user = new User();
				user.setUsername("9876543210");
				user.setName("Aman Deep");
				user.setEmail("amandeep@electrowaves.com");
				user.setPassword(passwordEncoder.encode("password123"));
				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);
				user.setRoles(roles);
				userRepository.save(user);
			}
		};
	}

}
