package com.raz.accounts.service;

import com.raz.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
