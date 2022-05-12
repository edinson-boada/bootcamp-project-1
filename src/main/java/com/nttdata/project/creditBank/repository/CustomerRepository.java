package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Flux<Customer> findByIdIn(List<String> customerIds);
}
