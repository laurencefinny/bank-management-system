package com.cts.bank.bankmanagementsystem.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bank.bankmanagementsystem.model.Customer;
import com.cts.bank.bankmanagementsystem.repository.CustomerRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {
	@Autowired
	CustomerRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/authenticate")
	public HashMap<String, String> authenticate(@RequestHeader(value = "Authorization") String authHeader) {
		HashMap<String, String> map = new HashMap<>();
		String user = getUser(authHeader);
		String[] name = user.split(":");
		String token = generateJwt(user);
		map.put("user", name[0]);
		Customer users = userRepository.findById(Integer.parseInt(name[0])).block();
		map.put("Account Type", users.getCustomerAccountType());
		map.put("token", token);
		logger.info("AuthenticationController class" + "in authenicate method() call");
		return map;
	}

	private String getUser(String authHeader) {
		String encoded = authHeader.substring(6).toString();
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String decoded = new String(decoder.decode(encoded));
		logger.info("AuthenticationController class" + "in getUser method() call");
		return decoded;
	}

	private String generateJwt(String user) {

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		// Set the token issue time as current time
		builder.setIssuedAt(new Date());

		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");

		String token = builder.compact();
		logger.info("AuthenticationController class" + "in generateJwt method() call");

		return token;

	}
}
