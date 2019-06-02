package io.swagger.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "TB_PAYMENT_HISTORY")
public class PaymentHistory {

	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("payment")
	@ManyToOne
	@JoinColumn(name = "id_payment")
	private Payment payment;

	@JsonProperty("date")
	@JsonFormat
	(shape = JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy hh:mm:ss")
	private Date date;
	
	@JsonProperty("status")
	private Payment.StatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Payment.StatusEnum getStatus() {
		return status;
	}

	public void setStatus(Payment.StatusEnum status) {
		this.status = status;
	}

}
