package com.appdeveloperblog.app.ws.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appdeveloperblog.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	public final UserService userDetailsService;
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
		}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll().anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	

}
