package com.cgpacalculator.CGPACalculator.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cgpacalculator.CGPACalculator.Entity.register;
import com.cgpacalculator.CGPACalculator.Repository.RegisterRepo;

@Component
public class CGPACalculatorAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	public RegisterRepo registerRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		List<register> allUsers=registerRepo.findAll();
		register activeUser=new register();
		
		for(register user : allUsers) {
			if(user.getEmail().equals(email)) {
				activeUser=user;
			}
		}
		
		if(activeUser.getEmail()!=null) {
			if(passwordEncoder.matches( password,activeUser.getPassword())) {
				List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("USER"));
				return new UsernamePasswordAuthenticationToken(email,password,authorities);
			}
			else {
				throw new BadCredentialsException("Wrong Password");
			}
		}
		else {
			throw new BadCredentialsException("No such user");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	
}
