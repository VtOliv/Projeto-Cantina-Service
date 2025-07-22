package com.project.cantina.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.cantina.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, Integer> {

}
