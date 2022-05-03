package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
