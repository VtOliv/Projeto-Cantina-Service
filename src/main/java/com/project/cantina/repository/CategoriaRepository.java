package com.project.cantina.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.cantina.domain.Categoria;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {

}
