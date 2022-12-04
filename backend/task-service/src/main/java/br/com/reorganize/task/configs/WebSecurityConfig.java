package br.com.reorganize.task.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic().and()
		.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/tasks/**").permitAll()
		.antMatchers(HttpMethod.POST, "/tasks").permitAll()
		.antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/tasks/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/users/**").permitAll()
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.antMatchers(HttpMethod.DELETE, "/users/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/users/**").permitAll()
		.anyRequest().authenticated().and().csrf().disable();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
