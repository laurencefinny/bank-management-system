package com.cts.bank.bankmanagementsystem.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bank.bankmanagementsystem.model.Customer;
import com.cts.bank.bankmanagementsystem.repository.CustomerRepository;
import com.cts.bank.bankmanagementsystem.services.CustomerService;

import reactor.core.publisher.Mono;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Mono<Customer> createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
