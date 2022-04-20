package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.TypeAccountNotFoundException;
import com.nttdata.project01.creditBank.model.Customer;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.service.CustomerService;
import com.nttdata.project01.creditBank.strategy.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> getCustomer(String id) {
        return Mono.just(customerRepository.findById(id).get());
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return Flux.fromIterable(customerRepository.findAll());
    }

    @Override
    public Mono<Customer> addCustomer(Customer customer) {
        validateTypeAccount(customer);
        return Mono.just(customerRepository.save(customer));
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer, String id) {
        customer.setId(id);
        return Mono.just(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    private void validateTypeAccount(Customer customer) {
        try {
            CustomerType.valueOf(customer.getType());
        } catch (IllegalArgumentException e) {
            throw new TypeAccountNotFoundException("Account type must be PERSONAL or BUSINESS.");
        }
    }
}
