package io.swagger.service;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.api.response.Response;
import io.swagger.model.Payment;

@Component
public interface PaymentService {
	
	Payment createOrUpdate(Payment payment);

	Response<List<Payment>> findAll();
	
	void process(Payment payment);

	Response<Payment> create(Payment payment);

	Response<Payment> findById(Long id);
}
