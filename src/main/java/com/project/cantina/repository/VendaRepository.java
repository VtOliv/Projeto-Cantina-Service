package com.project.cantina.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.cantina.domain.Carrinho;

@Repository
public interface VendaRepository extends MongoRepository<Carrinho, String> {

}
