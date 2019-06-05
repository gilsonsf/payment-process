package io.swagger.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.swagger.model.Buyer;

@Component
public interface BuyerService {
	
	Buyer createOrUpdate(Buyer buyer);
	
	Optional<Buyer> findByEmail(String email);


}
