package com.cvs.poc.SpringMongoPoc.repository;

import com.cvs.poc.SpringMongoPoc.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  PersonRepository extends MongoRepository<Person, Long> {
}
