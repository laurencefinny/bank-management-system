package com.cts.bank.bankmanagementsystem.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cts.bank.bankmanagementsystem.model.Customer;



public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
