package com.NagpurMetro.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.NagpurMetro.Service.MetroTicketService;
import com.NagpurMetro.ServiceImpl.MetroTicketServiceImpl;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	private MetroTicketServiceImpl metroticketserviceimpl;
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	//load user record and give to auth manager 
	
	
	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setPasswordEncoder(pwdEncoder());
		auth.setUserDetailsService(metroticketserviceimpl);
		return auth;
	}
	
	//it will manage that login user is valid or not
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration conf)
	{
		return conf.getAuthenticationManager();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http)
	{
		http.authorizeHttpRequests(request->{
			
			request.requestMatchers("/","/home","/generateQRCode","/successPage","/ticketdata","/registerUI","/ListTicket","/login","/register","/registershow","/loginUI","/tkt/new")
			.permitAll()
			.anyRequest()
			.authenticated();
			
		});
		return http.csrf().disable().build();
	}
	
}
