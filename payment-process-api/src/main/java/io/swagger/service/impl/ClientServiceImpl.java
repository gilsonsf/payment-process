package io.swagger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Client;
import io.swagger.repository.ClientRepository;
import io.swagger.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client createOrUpdate(Client client) {
		return this.clientRepository.save(client);
	}
	
}
