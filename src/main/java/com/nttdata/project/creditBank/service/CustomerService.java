package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.model.api.CustomerProductsResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> getCustomer(String id);
    Flux<Customer> getAllCustomers();
    Mono<CustomerProductsResponse> getCustomerProducts(String id);
    Mono<Customer> addCustomer(Customer customer);
    Mono<Customer> updateCustomer(Customer customer, String id);
    Mono<Customer> deleteCustomer(String id);
}
