package com.raz.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.raz.accounts.dto.CardsDto;

@Component
public class CardsFallback implements CardsFeignClient
{

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
		
		return null;
	}

}
