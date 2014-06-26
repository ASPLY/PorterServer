package org.a_sply.porter.config;

import org.a_sply.porter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * JavaConfig for configuring spring security  
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;
	
	/**
	 * Set UserDtailService that is used to authenticate the User
	 */

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.getPasswordEncoder());
	}
	
	/**
	 * Set url pattern to be authorized 
	 */

	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
		 .authorizeRequests()
		 .antMatchers("/images/**", "/articleLists/user", "/messages/**", "/messageLists/**", "/articles", "/products")
		 .hasRole("USER")
		 .anyRequest()
		 .anonymous()
		 .and()
		 .httpBasic();
	}
}
