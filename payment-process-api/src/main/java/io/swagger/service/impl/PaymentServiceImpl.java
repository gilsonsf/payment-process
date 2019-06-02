package io.swagger.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.api.response.Response;
import io.swagger.model.Buyer;
import io.swagger.model.Card;
import io.swagger.model.Client;
import io.swagger.model.Payment;
import io.swagger.model.Payment.TypeEnum;
import io.swagger.model.PaymentHistory;
import io.swagger.mq.MessageSender;
import io.swagger.mq.config.ApplicationConfig;
import io.swagger.repository.PaymentRepository;
import io.swagger.service.BuyerService;
import io.swagger.service.CardService;
import io.swagger.service.ClientService;
import io.swagger.service.PaymentHistoryService;
import io.swagger.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private MessageSender messageSender;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private CardService cardService;

	@Autowired
	private PaymentHistoryService paymentHistoryService;

	@Override
	public Response<Payment> create(Payment payment) {
		Response<Payment> response = new Response<Payment>();

		validate(payment, response);

		if (response.getErrors().isEmpty()) {

			Buyer currentBuyer = buyerService.createOrUpdate(payment.getBuyer());
			Client currentClient = clientService.createOrUpdate(payment.getClient());
			payment.setBuyer(currentBuyer);
			payment.setClient(currentClient);

			Card currentCard = null;
			if (TypeEnum.CREDIT_CARD == payment.getType()) {
				currentCard = cardService.createOrUpdate(payment.getCard());
				payment.setCard(currentCard);
			}

			Payment paymentResulted = createOrUpdate(payment);

			paymentHistoryService.createOrUpdate(getPaymentHistory(paymentResulted));

			process(paymentResulted);

			response.setData(paymentResulted);
		}

		return response;
	}

	@Override
	public Payment createOrUpdate(Payment payment) {
		return this.paymentRepository.save(payment);
	}

	@Override
	public Response<Payment> findById(Long id) {

		Response<Payment> response = new Response<Payment>();
		response.setData(paymentRepository.findOne(id));
		return response;
	}

	@Override
	public Response<List<Payment>> findAll() {

		Response<List<Payment>> response = new Response<List<Payment>>();
		response.setData(paymentRepository.findAll());
		return response;
	}

	@Override
	public void process(Payment payment) {

		String exchange = applicationConfig.getPaymentExchange();
		String routingKey = applicationConfig.getPaymentRoutingKey();

		/* Sending to Message Queue */
		try {
			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, payment);

		} catch (Exception ex) {
			LOG.error("Exception occurred while sending message to the queue. Exception= {}", ex);
		}
	}

	private PaymentHistory getPaymentHistory(Payment payment) {

		PaymentHistory paymentHistory = new PaymentHistory();

		paymentHistory.setDate(new Date());
		paymentHistory.setPayment(payment);
		paymentHistory.setStatus(payment.getStatus());

		return paymentHistory;
	}

	private void validate(Payment payment_, Response<Payment> response) {

		if (payment_.getCard() != null && !payment_.getCard().isValid()) {
			response.getErrors().add("Invalid Card Number " + payment_.getCard().getNumber());
			return;
		}

		Buyer currentBuyer = buyerService.findByEmail(payment_.getBuyer().getEmail());
		if (currentBuyer != null) {
			response.getErrors().add(payment_.getBuyer().getEmail() + " already in use! Please try another one.");
			return;
		}
	}

}
