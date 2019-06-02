package io.swagger.mq;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import io.swagger.model.Payment;
import io.swagger.model.PaymentHistory;
import io.swagger.model.Payment.StatusEnum;
import io.swagger.mq.config.ApplicationConfig;
import io.swagger.mq.util.ApplicationConstant;
import io.swagger.service.PaymentHistoryService;
import io.swagger.service.PaymentService;

@Service
public class MessageListener {

	private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

	@Autowired
	ApplicationConfig applicationConfig;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentHistoryService paymentHistoryService;

	/**
	 * Message listener
	 * 
	 * @param UserDetails a user defined object used for deserialization of message
	 */
	@RabbitListener(queues = "${payment.queue.name}")
	public void receiveMessageForPayment(final Payment data) {
		log.info("Received message: {} from payment queue.", data);

		try {
			log.info("Process Payment Number: " + data.getId());

			Thread.sleep(30000); // waiting 30 seconds before change status

			data.setStatus(StatusEnum.IN_ANALYSIS);
			Payment currentPayment = paymentService.createOrUpdate(data);
			paymentHistoryService.createOrUpdate(getPaymentHistory(currentPayment));

			Thread.sleep(30000); // waiting 30 seconds before change status

			if (data.getId() % 2 == 0) {
				currentPayment.setStatus(StatusEnum.AUTHORIZED);
			} else {
				currentPayment.setStatus(StatusEnum.CANCELLED);
			}

			currentPayment = paymentService.createOrUpdate(currentPayment);
			paymentHistoryService.createOrUpdate(getPaymentHistory(currentPayment));

			log.info("Finalize Process Payment Number: " + data.getId());
		} catch (HttpClientErrorException ex) {

			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.info("Delay...");
				try {
					Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}

				log.info("Throwing exception so that message will be requed in the queue.");
				// Note: Typically Application specific exception should be thrown below
				throw new RuntimeException();
			} else {
				throw new AmqpRejectAndDontRequeueException(ex);
			}

		} catch (Exception e) {
			log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
			throw new AmqpRejectAndDontRequeueException(e);
		}

	}

	private PaymentHistory getPaymentHistory(Payment payment) {

		PaymentHistory paymentHistory = new PaymentHistory();

		paymentHistory.setDate(new Date());
		paymentHistory.setPayment(payment);
		paymentHistory.setStatus(payment.getStatus());

		return paymentHistory;
	}
}
