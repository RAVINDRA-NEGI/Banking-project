package com.raz.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.raz.accounts.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient{

	@Override
	public ResponseEntity<LoansDto> fetchLoansDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

}
