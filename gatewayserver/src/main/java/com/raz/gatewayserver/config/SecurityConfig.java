package com.raz.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain  springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
				.pathMatchers("bank/accounts/**").hasRole("ACCOUNTS")
				.pathMatchers("bank/loans/**").hasRole("LOANS")
				.pathMatchers("bank/cards/**").hasRole("CARDS"))
				.oauth2ResourceServer(oAuth2ResourceServer -> oAuth2ResourceServer
						.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))
			.csrf(csrfSpe -> csrfSpe.disable());
				
		return http.build();
		
	}
	
	private Converter<Jwt,Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
		JwtAuthenticationConverter jwtAuthenticationConverter = 
				new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
				(new KeycloakRoleConverter());
		return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
	}
	
}

