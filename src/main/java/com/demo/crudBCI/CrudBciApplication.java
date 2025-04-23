package com.demo.crudBCI;

import com.demo.crudBCI.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class CrudBciApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBciApplication.class, args);
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

	   http
	          .csrf((csrf) -> csrf.disable())
	         .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .authorizeHttpRequests( authz -> authz
					.requestMatchers(HttpMethod.POST,"/api/create").permitAll()
					.requestMatchers("/error").permitAll()
	                .anyRequest().authenticated());
	 return http.build();
	 }
}
