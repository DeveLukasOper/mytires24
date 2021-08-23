package com.example.demo.repository;

import com.example.demo.model.Kunde;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KundeRepository extends MongoRepository<Kunde, String>{

}
