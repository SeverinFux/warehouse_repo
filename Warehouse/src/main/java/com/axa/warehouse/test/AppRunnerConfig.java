package com.axa.warehouse.test;

import com.axa.warehouse.repository.RoleRepository;
import com.axa.warehouse.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.axa.warehouse.model.Role;

import java.util.stream.Collectors;

@Configuration
public class AppRunnerConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            // Ausgabe aller Rollen
            System.out.println("Rollen in der Datenbank:");
            roleRepository.findAll().forEach(role -> System.out.println("Role: " + role.getName()));

            // Ausgabe aller Benutzer und ihrer Rollen
            System.out.println("\nBenutzer in der Datenbank:");
            userRepository.findAll().forEach(user -> {
                String roles = user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(", "));
                System.out.println("Username: " + user.getUsername() + ", Roles: " + roles);
            });
        };
    }
}
