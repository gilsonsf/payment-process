package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

}
