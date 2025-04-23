package com.raz.accounts.service.client;

import org.springframework.http.ResponseEntity;

import com.raz.accounts.dto.CardsDto;

public class CardsFallback implements CardsFeignClient
{

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

}
