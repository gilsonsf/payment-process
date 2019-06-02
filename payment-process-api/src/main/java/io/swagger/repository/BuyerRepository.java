package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Long>{
	
	Buyer findByEmail(String email);

}
