package io.swagger.service;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.model.PaymentHistory;

@Component
public interface PaymentHistoryService {
	
	PaymentHistory createOrUpdate(PaymentHistory paymentHistory);
	
	List<PaymentHistory> findHistoryByPaymentId(Long paymentId);

}
