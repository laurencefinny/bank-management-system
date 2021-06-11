package com.cts.bank.bankmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.bank.bankmanagementsystem.model.Customer;
import com.cts.bank.bankmanagementsystem.repository.CustomerRepository;

import reactor.core.publisher.Mono;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerRepository userRepository;

	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		Customer user = userRepository.findById(Integer.parseInt(loginId)).block();
		if (user.getCustomerId() == null) {
			throw new UsernameNotFoundException(loginId);
		} else {
			AppUser appUser = new AppUser(user);
			return appUser;
		}
	}
}
