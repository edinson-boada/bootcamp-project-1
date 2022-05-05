package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
