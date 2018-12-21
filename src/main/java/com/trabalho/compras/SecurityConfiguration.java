package com.trabalho.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("lucas").password("lucas").roles("USER").and()
			.withUser("jessica").password("jessica").roles("USER", "MANAGER").and()
			.withUser("raika").password("raika").roles("USER", "MANAGER", "ADMIN");
	}      

	@Override
	  public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().requestMatchers().antMatchers("/login", "/oauth/authorize").and()
            .authorizeRequests().anyRequest().authenticated().and()
            .formLogin().permitAll();
	  }
}