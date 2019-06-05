package io.swagger.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Buyer;
import io.swagger.repository.BuyerRepository;
import io.swagger.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerRepository buyerRepository;
	
	@Override
	public Buyer createOrUpdate(Buyer buyer) {
		return this.buyerRepository.save(buyer);
	}

	@Override
	public Optional<Buyer> findByEmail(String email) {
		return this.buyerRepository.findByEmail(email);
	}
	
}
