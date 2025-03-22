package com.raz.accounts.service;

import com.raz.accounts.dto.CustomerDto;

public interface IAccountService {
		
	
	void createAccount(CustomerDto customerDto);
	
	CustomerDto fetchAccount(String mobileNumber);
	
	boolean updateAccount(CustomerDto customerDto);
	
	boolean deleteAccount (String mobileNumber);
	
}
