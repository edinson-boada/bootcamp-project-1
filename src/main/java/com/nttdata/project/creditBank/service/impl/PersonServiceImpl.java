package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.model.Person;
import com.nttdata.project.creditBank.repository.PersonRepository;
import com.nttdata.project.creditBank.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository PersonRepository;

    @Override
    public Mono<Person> getPerson(String id) {
        return PersonRepository.findById(id);
    }

    @Override
    public Flux<Person> getAllPersons() {
        return PersonRepository.findAll();
    }

    @Override
    public Mono<Person> addPerson(Person Person) {
        return PersonRepository.save(Person);
    }

    @Override
    public Mono<Person> updatePerson(Person Person, String id) {
        Person.setId(id);
        return PersonRepository.save(Person);
    }

    @Override
    public Mono<Person> deletePerson(String id) {
        return PersonRepository.findById(id)
                .flatMap(deletedPerson -> PersonRepository.delete(deletedPerson)
                        .then(Mono.just(deletedPerson)));
    }
}
