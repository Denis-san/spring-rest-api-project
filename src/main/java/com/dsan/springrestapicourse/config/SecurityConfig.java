package com.dsan.springrestapicourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private static final String[] PUBLIC_MATCHERS = { "/products/**", "/categories/**" };

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();

		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

		return source;

	}
}
