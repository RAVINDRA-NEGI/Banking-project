package com.raz.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class AccountsDto {
	
	@NotEmpty(message = "AccountNumber can not be a null or empty")
	@Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
	@Schema(
			description = "Account Number of Bank Account" , example = "4657824197"
			)
	private Long accountNumber;
	
   	
	@Schema(
			description ="Account type of Bank Account " , example = "Savings"
			)
	@NotEmpty(message = "AccountType can not be a null or empty")
   	private String accountType;
	
	
	@Schema(
			description ="Bank branch Address" , example = "123 Dehradun"
			)
	@NotEmpty(message = "BranchAddress can not be a null or empty")
   	private String branchAddress;
}
