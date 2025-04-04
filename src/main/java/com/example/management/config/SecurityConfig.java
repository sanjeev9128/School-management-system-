package com.example.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("cal useDetails*******");
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(User.withUsername("studentUser").password(passwordEncoder().encode("studentPassword"))
				.roles("STUDENT").build());

		manager.createUser(User.withUsername("teacherUser").password(passwordEncoder().encode("teacherPassword"))
				.roles("TEACHER").build());

		return manager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF for Postman testing
				.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/students/**").hasRole("STUDENT") // Allow GET
				.requestMatchers(HttpMethod.POST, "/students/**").hasRole("STUDENT") // Allow

				.requestMatchers("/teachers/**").hasRole("TEACHER").requestMatchers("/login").permitAll().anyRequest()
				.authenticated().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

}
