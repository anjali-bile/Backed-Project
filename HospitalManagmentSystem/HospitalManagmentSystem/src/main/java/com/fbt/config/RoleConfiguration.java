package com.fbt.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fbt.entity.Role;
import com.fbt.repository.RoleRepository;

@Configuration
public class RoleConfiguration {

//	@Autowired
//	private RoleRepository roleRepository;

	@Bean
	public CommandLineRunner loadDefaultRoles(RoleRepository roleRepository) {
		return args -> {
			List<String> defaultRoles = Arrays.asList("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_PATIENT", "ROLE_RECEPTIONIST",
					"ROLE_NURSE");

			int idCounter = 1;
			for (String roleName : defaultRoles) {
				if (!roleRepository.existsByName(roleName)) {
					roleRepository.save(new Role(idCounter++, roleName));
				}
			}
		};
	}

//	@Bean
//	public CommandLineRunner loadDefaultAdmin(UserRepository userRepository) {
//		return args -> {
//			Optional<Role> roleOp = roleRepository.findById(3);
//
//			User user = new User(1, "admin@gmail.com", 1234567890, "admin", "admin@123", roleOp.get(), true);
//			userRepository.save(user);
//
//		};
//	}

}
