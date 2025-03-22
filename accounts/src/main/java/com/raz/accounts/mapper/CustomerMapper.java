package com.raz.accounts.mapper;

import com.raz.accounts.dto.CustomerDto;
import com.raz.accounts.entity.Customer;

public class CustomerMapper {
		
	public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
	customerDto.setCustomerName(customer.getCustomerName());
	customerDto.setCustomerEmail(customer.getCustomerEmail());
	customerDto.setCustomerMobileNumber(customer.getCustomerMobileNumber());
	return customerDto;
	}
	public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
	customer.setCustomerName(customerDto.getCustomerName());
	customer.setCustomerEmail(customerDto.getCustomerEmail());
	customer.setCustomerMobileNumber(customerDto.getCustomerMobileNumber());
	return customer;
}

}