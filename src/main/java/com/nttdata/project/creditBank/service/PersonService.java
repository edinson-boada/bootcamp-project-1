package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> getPerson(String id);
    Flux<Person> getAllPersons();
    Mono<Person> addPerson(Person Person);
    Mono<Person> updatePerson(Person Person, String id);
    Mono<Person> deletePerson(String id);

}
