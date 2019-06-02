package io.swagger.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.PaymentHistory;


public interface PaymentHistoryRepository extends CrudRepository <PaymentHistory, Long> {
	
	List<PaymentHistory> findByPaymentIdOrderByDateDesc(Long paymentId);

}
