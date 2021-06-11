package com.cts.bank.bankmanagementsystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bank.bankmanagementsystem.model.Customer;
import com.cts.bank.bankmanagementsystem.model.Loan;
import com.cts.bank.bankmanagementsystem.repository.CustomerRepository;
import com.cts.bank.bankmanagementsystem.repository.LoanRepository;
import com.cts.bank.bankmanagementsystem.service.impl.CustomerServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/jer")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@PostMapping("/createCustomer")
	public Mono<Object> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		customer.setCustomerPassword(passwordEncoder().encode(customer.getCustomerPassword()));
		return customerRepository.save(customer).map(savedCustomer -> ResponseEntity.ok(savedCustomer.getCustomerId()));

	}

	private PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	@GetMapping("/all")
	public Flux<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	@PostMapping("/createLoan")
	public Mono<Object> createLoan(@RequestBody Loan loan) {
		return loanRepository.save(loan).map(savedLoanDetails -> ResponseEntity.ok(savedLoanDetails.getLoanId()));

	}

//	  @GetMapping("/{customerId}") public Flux<Loan> listLoanDetails(@PathVariable
//	  int customerId){ return loanRepository.find }

	@GetMapping("/{customerId}")
	public Flux<ResponseEntity<Loan>> getEventByEmpId(@PathVariable int customerId) {
		return loanRepository.findByCustId(customerId).map(event -> ResponseEntity.ok().body(event))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

}
