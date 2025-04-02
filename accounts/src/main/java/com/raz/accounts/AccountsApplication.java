package com.raz.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.raz.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
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
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
