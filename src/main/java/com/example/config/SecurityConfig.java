package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.service.UserService;

//klasa konfiguracji springa
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final int PASSWORD_STRENGHT = 10;
	
	@Autowired
	private UserService userService;
	
	//konfiguracja autoryzacji
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(PASSWORD_STRENGHT));
	}
	
	//konfuguracja uprawnien zwiazanych metodami restowymi (kontrolerow)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/users/**", "/create-user").hasRole("ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/api/**").permitAll()
			.antMatchers("/**").authenticated()
			.and()
				.formLogin()
				.usernameParameter("email")
				.passwordParameter("password")
				.loginPage("/login")
			
			.and()
				.logout()
				.logoutUrl("/logout")
			.and()
				.csrf().disable();			
	}	
}
