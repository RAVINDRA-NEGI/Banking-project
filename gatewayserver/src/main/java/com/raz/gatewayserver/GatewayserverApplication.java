package com.raz.gatewayserver;


import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator bankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
	    return routeLocatorBuilder.routes()
	            .route(p -> p
	                    .path("/bank/accounts/**")
	                    .filters(f -> f.rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}")
	                    		.addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
	                    		.circuitBreaker(config -> config.setName("accountCircuitBreaker")
	                    				.setFallbackUri("forward:/contactSupport")))             		
	                    .uri("lb://ACCOUNTS"))
	            .route(p -> p
	                    .path("/bank/loans/**")
	                    .filters(f -> f.rewritePath("/bank/loans/(?<segment>.*)", "/${segment}")
	                    		.addRequestHeader("X-Response-Time", LocalDateTime.now().toString())) 
	                    .uri("lb://LOANS"))
	            .route(p -> p
	                    .path("/bank/cards/**")
	                    .filters(f -> f.rewritePath("/bank/cards/(?<segment>.*)", "/${segment}")
	                    		.addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
	                    		.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
	                    				.setKeyResolver(userKeyResolver())))
	                    		
	                    .uri("lb://CARDS"))
	            .build();
	}

	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
			.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
			.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10)).build()).build());
	}
	
	
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1,1,1);
	}
	
	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}
	
	
}
