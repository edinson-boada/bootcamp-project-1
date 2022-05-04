package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.CustomerTypeNotFoundException;
import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.repository.CustomerRepository;
import com.nttdata.project.creditBank.service.CustomerService;
import com.nttdata.project.creditBank.strategy.CustomerType;
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
        return customerRepository.findById(id);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> addCustomer(Customer customer) {
        validateCustomerType(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer, String id) {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public void validateCustomerType(Customer customer) {
        try {
            CustomerType.valueOf(customer.getType());
        } catch (IllegalArgumentException e) {
            throw new CustomerTypeNotFoundException("Customer type must be PERSONAL or BUSINESS.");
        }
    }
}
