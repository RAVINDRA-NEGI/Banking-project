package com.raz.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.raz.accounts.dto.AccountsDto;
import com.raz.accounts.dto.CardsDto;
import com.raz.accounts.dto.CustomerDetailsDto;
import com.raz.accounts.dto.LoansDto;
import com.raz.accounts.entity.Accounts;
import com.raz.accounts.entity.Customer;
import com.raz.accounts.exception.ResourceNotFoundException;
import com.raz.accounts.mapper.AccountsMapper;
import com.raz.accounts.mapper.CustomerMapper;
import com.raz.accounts.repository.AccountsRepository;
import com.raz.accounts.repository.CustomerRepository;
import com.raz.accounts.service.ICustomersService;
import com.raz.accounts.service.client.CardsFeignClient;
import com.raz.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {
	
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	
	
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber , String correlationId) {
		Customer customer =	customerRepository.findByCustomerMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
	Accounts accounts =	accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		
	  CustomerDetailsDto  customerDetailsDto =  CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
	  customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

	  ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoansDetails(correlationId , mobileNumber);
	  customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
	
	  ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
	  customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
	
	  
	  return customerDetailsDto;
	}
}
