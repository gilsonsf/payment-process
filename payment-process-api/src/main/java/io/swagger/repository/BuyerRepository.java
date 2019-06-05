package io.swagger.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Long>{
	
	Optional<Buyer> findByEmail(String email);

}
