package io.swagger.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	
	@Value("${payment.exchange.name}")
	private String paymentExchange;
	
	@Value("${payment.queue.name}")
	private String paymentQueue;
	
	@Value("${payment.routing.key}")
	private String paymentRoutingKey;

	public String getPaymentExchange() {
		return paymentExchange;
	}

	public void setPaymentExchange(String paymentExchange) {
		this.paymentExchange = paymentExchange;
	}

	public String getPaymentQueue() {
		return paymentQueue;
	}

	public void setPaymentQueue(String paymentQueue) {
		this.paymentQueue = paymentQueue;
	}

	public String getPaymentRoutingKey() {
		return paymentRoutingKey;
	}

	public void setPaymentRoutingKey(String paymentRoutingKey) {
		this.paymentRoutingKey = paymentRoutingKey;
	}
	
}
