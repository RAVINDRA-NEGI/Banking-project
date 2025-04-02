package com.raz.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.raz.loans.dto.LoansContactInfoDto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				 title = "Accounts microservice REST API Documentation",
				 description = "Bank Accounts microservice REST API Documentatin",
				 version = "v1",
				 contact = @Contact(
						 name = "Raz Negi",
						 email = "ravindrasinghnegi68@gmail.com",
						 url = "https://github.com/RAVINDRA-NEGI"
				)
			)
		)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
