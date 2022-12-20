package com.cgpacalculator.CGPACalculator.Configuration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception{
		
		http.cors().configurationSource(new CorsConfigurationSource(){
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration config=new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setMaxAge(6000L);
				return config;
			}

		}).and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/hello","/dashboard","/deptsemester/**","/deptsemester/converter").authenticated()
		.requestMatchers("/register","/subject").permitAll()
		.and().formLogin()
		.and().httpBasic();
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
