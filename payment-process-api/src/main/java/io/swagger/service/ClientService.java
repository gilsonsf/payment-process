package io.swagger.service;

import org.springframework.stereotype.Component;

import io.swagger.model.Client;

@Component
public interface ClientService {
	
	Client createOrUpdate(Client client);


}
