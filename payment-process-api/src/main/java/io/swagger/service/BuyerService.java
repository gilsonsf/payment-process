package io.swagger.service;

import org.springframework.stereotype.Component;

import io.swagger.model.Buyer;

@Component
public interface BuyerService {
	
	Buyer createOrUpdate(Buyer buyer);
	
	Buyer findByEmail(String email);


}
