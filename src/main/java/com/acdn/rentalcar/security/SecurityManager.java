/*package com.acdn.rentalcar.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityManager extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	        .inMemoryAuthentication()
	        .withUser("garrincha").password("123")
	            .roles("USER")
	        .and()
	        .withUser("zico").password("123")
	            .roles("USER");
	  }
	
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            // Para qualquer requisição (anyRequest) é preciso estar 
	            // autenticado (authenticated).
	            .anyRequest().permitAll()
	        .and()
	        .formLogin()
	        	.loginPage("/acdn/cadastro")
	        	.permitAll()
	        .and()
	        .httpBasic();
	  }
	
	

}*/
