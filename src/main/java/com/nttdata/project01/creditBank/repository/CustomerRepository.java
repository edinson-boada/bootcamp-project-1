package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
