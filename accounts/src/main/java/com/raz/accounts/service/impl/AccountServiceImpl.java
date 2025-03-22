package com.raz.accounts.service.impl;


import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.raz.accounts.constants.AccountsConstants;
import com.raz.accounts.dto.AccountsDto;
import com.raz.accounts.dto.CustomerDto;
import com.raz.accounts.entity.Accounts;
import com.raz.accounts.entity.Customer;
import com.raz.accounts.exception.CustomerAlreadyExistsException;
import com.raz.accounts.exception.ResourceNotFoundException;
import com.raz.accounts.mapper.AccountsMapper;
import com.raz.accounts.mapper.CustomerMapper;
import com.raz.accounts.repository.AccountsRepository;
import com.raz.accounts.repository.CustomerRepository;
import com.raz.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{
	
	private final AccountsRepository accountRepo;
	private final CustomerRepository customerRepo;
	

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer =	customerRepo.findByCustomerMobileNumber(customerDto.getCustomerMobileNumber());
	    if(optionalCustomer.isPresent()) {
	    	throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber" 
	    												+ customerDto.getCustomerMobileNumber());
	    }
		Customer savedCustomer  = customerRepo.save(customer);
		
		accountRepo.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
	Customer customer =	customerRepo.findByCustomerMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
	Accounts accounts =	accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
	
	CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
	customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
	return customerDto;
	
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		 boolean isUpdated = false;
	        AccountsDto accountsDto = customerDto.getAccountsDto();
	        if(accountsDto !=null ){
	            Accounts accounts = accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
	                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
	            );
	            AccountsMapper.mapToAccounts(accountsDto, accounts);
	            accounts = accountRepo.save(accounts);

	            Long customerId = accounts.getCustomerId();
	            Customer customer = customerRepo.findById(customerId).orElseThrow(
	                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
	            );
	            CustomerMapper.mapToCustomer(customerDto,customer);
	            customerRepo.save(customer);
	            isUpdated = true;
	        }
	        return  isUpdated;
		
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
	Customer customer = customerRepo.findByCustomerMobileNumber(mobileNumber).orElseThrow(
			() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
			);
			accountRepo.deleteByCustomerId(customer.getCustomerId());
			customerRepo.deleteById(customer.getCustomerId());
		return false;
	}
	
}































