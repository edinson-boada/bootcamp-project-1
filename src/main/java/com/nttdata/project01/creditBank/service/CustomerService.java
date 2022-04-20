package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> getCustomer(String id);
    Flux<Customer> getAllCustomers();
    Mono<Customer> addCustomer(Customer customer);
    Mono<Customer> updateCustomer(Customer customer, String id);
    void deleteCustomer(String id);

    Mono<Customer> findById(String id);
}
