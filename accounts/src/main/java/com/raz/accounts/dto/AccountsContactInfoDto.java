package com.raz.accounts.dto;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="accounts")
public record AccountsContactInfoDto(String message,java.util.Map<String,String>contactDetails,List<String> onCallSupport ) {
	
}
