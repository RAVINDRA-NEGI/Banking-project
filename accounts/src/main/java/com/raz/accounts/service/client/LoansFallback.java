package com.raz.accounts.service.client;

import org.springframework.http.ResponseEntity;

import com.raz.accounts.dto.LoansDto;

public class LoansFallback implements LoansFeignClient{

	@Override
	public ResponseEntity<LoansDto> fetchLoansDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

}
