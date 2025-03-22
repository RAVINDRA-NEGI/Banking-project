package com.raz.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Acount information"
		)
public class CustomerDto {
	
	@Schema(
			description = "Name of the customer" , example = " raj negi"
			)
	@NotEmpty(message = "Name can not be a null or empty")
	@Size(min = 3, max = 30 , message = " The  length of the customer name should be between 3 and 30")
	private String customerName;
	
	
	@Schema(
			description = "Email address of the customer" , example = "popcorn@gmail.com"
			)
   @NotEmpty(message = "Email address can not be a null or empty")
   @Email(message = "Email address should be a valid value")
   	private String customerEmail;
	
	
	@Schema(
			description = "Moblile Number of the customer" , example = "9917214498"
			)
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
   	private String customerMobileNumber;
	
	
	@Schema(
			description = "Detail of the Account" 
			)
   	private AccountsDto accountsDto;
}
