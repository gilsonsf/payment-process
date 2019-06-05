package io.swagger.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.swagger.api.response.Response;
import io.swagger.model.Buyer;
import io.swagger.model.Card;
import io.swagger.model.Client;
import io.swagger.model.Payment;
import io.swagger.model.Payment.StatusEnum;
import io.swagger.model.PaymentHistory;
import io.swagger.mq.MessageSender;
import io.swagger.mq.config.ApplicationConfig;
import io.swagger.repository.PaymentRepository;
import io.swagger.service.impl.PaymentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
	
	public static final Long PAYMENT_ID = 102L;
	public static final Long BUYER_ID = 34L;
	public static final String CARD_NUMBER = "5555666677778884";
	public static final String CLIENT_ID = "GHWY5J45TLO";
	
	@Before
	public void setUp() {
		
	    Payment payment = getPayment();
	    Buyer buyer = getBuyer();
	    Card card = getCard();
	    Client client = getClient();
	 
		Mockito.when(paymentRepository.findOne(PAYMENT_ID)).thenReturn(payment);
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		Mockito.when(buyerService.createOrUpdate(buyer)).thenReturn(buyer);
		Mockito.when(cardService.createOrUpdate(card)).thenReturn(card);
		Mockito.when(clientService.createOrUpdate(client)).thenReturn(client);
		Mockito.when(paymentHistoryService.createOrUpdate(new PaymentHistory())).thenReturn(new PaymentHistory());
		Mockito.when(applicationConfig.getPaymentExchange()).thenReturn("exchange");
		Mockito.when(applicationConfig.getPaymentRoutingKey()).thenReturn("routingKey");
		Mockito.doNothing().when(messageSender).sendMessage(null, null, null, null);
		
	}

	@InjectMocks
	private PaymentService paymentService = new PaymentServiceImpl();
 
	@Mock
	private PaymentHistoryService paymentHistoryService;

	@Mock
	private BuyerService buyerService;

	@Mock
	private CardService cardService;

	@Mock
	private ClientService clientService;

	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private ApplicationConfig applicationConfig;
	
	@Mock
	private MessageSender messageSender;
    
    @Test
    public void whenValidId_thenPaymentShouldBeFound() {
        Long validPaymentId = 102L;
        Response<Payment> found = paymentService.findById(validPaymentId);
        assertThat(found.getData().getId()).isEqualTo(PAYMENT_ID);
     }

    @Test
    public void whenInvalidId_thenPaymentShouldNotBeFound() {
    	Long invalidPaymentId = 200L;
    	Response<Payment> found = paymentService.findById(invalidPaymentId);
    	
    	assertNull(found.getData());
    }

    @Test
    public void whenDuplicateEmail_thenPaymentShouldNotBeCreate() {
    	Payment payment = getPayment();
    	Buyer buyer = getBuyer();
    	String email = "kawhi.leonard@gmail.com";
		buyer.setEmail(email);
		payment.setBuyer(buyer);
		
    	Mockito.when(buyerService.findByEmail(email)).thenReturn(Optional.of(getBuyer()));
    	Response<Payment> found = paymentService.create(payment);
    	
    	assertFalse(found.getErrors().isEmpty());
    }

    @Test
    public void whenInvalidCard_thenPaymentShouldNotBeCreate() {
    	Payment payment = getPayment();
    	Card card = getCard();
    	card.setValid(false);
    	payment.setCard(card);
    	
    	Mockito.when(cardService.createOrUpdate(getCard())).thenReturn(card);
    	Response<Payment> found = paymentService.create(payment);
    	
    	assertFalse(found.getErrors().isEmpty());
    }
    
    private Buyer getBuyer() {
		Buyer buyer = new Buyer();
	    buyer.setId(BUYER_ID);
		return buyer;
	}
    
    private Payment getPayment() {
		Payment payment = new Payment();
	    payment.setId(PAYMENT_ID);
	    payment.setAmount(220.0);
	    payment.setBuyer(getBuyer());
	    payment.setCard(getCard());
	    payment.setClient(getClient());
	    payment.setStatus(StatusEnum.CREATED); 
	    
		return payment;
	}
    
    private Card getCard() {
    	Card card = new Card();
    	card.setNumber(CARD_NUMBER);
    	card.setValid(true);
    	return card;
    }

    private Client getClient() {
		Client client = new Client();
	    client.setId(CLIENT_ID);
		return client;
	}

}
