package io.swagger.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.api.response.Response;
import io.swagger.model.Payment;
import io.swagger.model.PaymentHistory;
import io.swagger.service.PaymentHistoryService;
import io.swagger.service.PaymentService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-25T23:08:44.329Z")

@Controller
public class PaymentApiController implements PaymentApi {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentHistoryService paymentHistoryService;


	public ResponseEntity<Response<Payment>> create(@ApiParam(value = "") @Valid @RequestBody Payment payment_) {

		try {
			Response<Payment> response = paymentService.create(payment_);

			if (response.getErrors().isEmpty()) {
				return new ResponseEntity<Response<Payment>>(response, HttpStatus.CREATED);
			} else {
				return ResponseEntity.badRequest().body(response);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response<Payment>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Response<Payment>> find(@ApiParam(value = "", required = true) @PathVariable("id") Long id) {

		try {
			Response<Payment> response = paymentService.findById(id);
			return new ResponseEntity<Response<Payment>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response<Payment>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Response<List<Payment>>> findAll() {
		try {
			Response<List<Payment>> response = paymentService.findAll();
			return new ResponseEntity<Response<List<Payment>>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response<List<Payment>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Response<List<PaymentHistory>>> findPaymentHistory(
			@ApiParam(value = "", required = true) @PathVariable("id") Long id) {
		try {
			Response<List<PaymentHistory>> response = new Response<List<PaymentHistory>>();
			List<PaymentHistory> paymentHistory = paymentHistoryService.findHistoryByPaymentId(id);
			response.setData(paymentHistory);
			return new ResponseEntity<Response<List<PaymentHistory>>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response<List<PaymentHistory>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
