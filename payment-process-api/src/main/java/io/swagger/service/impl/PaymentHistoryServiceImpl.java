package io.swagger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.PaymentHistory;
import io.swagger.repository.PaymentHistoryRepository;
import io.swagger.service.PaymentHistoryService;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;

	@Override
	public PaymentHistory createOrUpdate(PaymentHistory paymentHistory) {
		return this.paymentHistoryRepository.save(paymentHistory);
	}
	
	@Override
	public List<PaymentHistory> findHistoryByPaymentId(Long paymentId) {
		return this.paymentHistoryRepository.findByPaymentIdOrderByDateDesc(paymentId);
	}


}
