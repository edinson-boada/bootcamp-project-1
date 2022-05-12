package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}
