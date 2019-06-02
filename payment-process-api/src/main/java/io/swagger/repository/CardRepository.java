package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {

}
