package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Reifen;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReifenRepository extends MongoRepository<Reifen, String>{
    List<Reifen> findByNameContaining(String name);

}
