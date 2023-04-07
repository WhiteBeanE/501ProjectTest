package com.oracle.ProjectTest.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableWebSecurity
//필터 체인 관리 시작 어노테이션
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bencodePwd() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().anyRequest().permitAll();
		
		return httpSecurity.build();
	}
	
}
