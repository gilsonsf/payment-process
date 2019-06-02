package io.swagger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

	List<Payment> findAll();

}
