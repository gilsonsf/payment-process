package io.swagger.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.swagger.api.response.Response;
import io.swagger.model.Card;
import io.swagger.service.impl.CardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

	public static final String VALID_CARD_NUMBER = "5555666677778884";
	public static final String INVALID_CARD_NUMBER = "129738465";

	private CardService cardService;

	@Before
	public void setUp() {
		cardService = new CardServiceImpl();
	}

	@Test
	public void whenValidNumber_thenCardValidateShouldBeTrue() {
		Response<Card> found = cardService.validate(VALID_CARD_NUMBER);
		assertThat(found.getData().isValid()).isEqualTo(true);
	}

	@Test
	public void whenInvalidNumber_thenCardValidateShouldBeFalse() {
		Response<Card> found = cardService.validate(INVALID_CARD_NUMBER);
		assertThat(found.getData().isValid()).isEqualTo(false);
	}

}
