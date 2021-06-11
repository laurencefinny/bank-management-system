package com.cts.bank.bankmanagementsystem.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.bank.bankmanagementsystem.model.Customer;

@SuppressWarnings("serial")
public class AppUser implements UserDetails {

	private Customer user; // entity reference
	private Collection<? extends GrantedAuthority> authorities; // to store role
																// details

	public AppUser(Customer user) {
		// TODO Auto-generated constructor stub
		List<String> roles = new ArrayList<>();
		this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getCustomerPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getCustomerFirstName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
