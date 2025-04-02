package com.raz.loans.dto;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="cards")
public record LoansContactInfoDto(String message,java.util.Map<String,String>contactDetails,List<String> onCallSupport) {

}
