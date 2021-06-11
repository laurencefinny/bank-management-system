package com.cts.bank.bankmanagementsystem.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "customer")
@Table(name = "customer")
public class CustomerEntity {
	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "customer_email_address")
	private String customerEmailAddress;

	@Column(name = "customer_first_name")
	private String customerFirstName;

	@Column(name = "customer_last_name")
	private String customerLastName;

	@Column(name = "customer_password")
	private String customerPassword;

	@Column(name = "customer_address")
	private String customerAddress;

	@Column(name = "customer_street")
	private String customerStreet;

	@Column(name = "customer_country")
	private String customerCountry;

	@Column(name = "customer_pan")
	private String customerPan;

	@Column(name = "customer_contact_no")
	private String customerContactNo;

	@Column(name = "customer_dob")
	private LocalDate customerDob;

	@Column(name = "customer_account_type")
	private String customerAccountType;
}
