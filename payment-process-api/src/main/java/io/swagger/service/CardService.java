package io.swagger.service;

import org.springframework.stereotype.Component;

import io.swagger.api.response.Response;
import io.swagger.model.Card;

@Component
public interface CardService {
	
	Card createOrUpdate(Card card);

	Response<Card> validate(String number);


}
