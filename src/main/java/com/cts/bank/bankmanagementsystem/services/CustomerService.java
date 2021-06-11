package com.cts.bank.bankmanagementsystem.services;


import com.cts.bank.bankmanagementsystem.model.Customer;

import reactor.core.publisher.Mono;

public interface CustomerService {
	public Mono<Customer> createCustomer(Customer customer);
}
