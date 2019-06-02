package io.swagger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.validators.CreditCard;
import io.swagger.api.response.Response;
import io.swagger.model.Card;
import io.swagger.repository.CardRepository;
import io.swagger.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	@Override
	public Card createOrUpdate(Card card) {
		return this.cardRepository.save(card);
	}

	@Override
	public Response<Card> validate(String number) {

		Response<Card> response = new Response<Card>();

		CreditCard creditCard = new CreditCard(number);
		Card card = new Card(number, creditCard.getBrand().toString(), creditCard.isValid());
		response.setData(card);

		return response;
	}

}
