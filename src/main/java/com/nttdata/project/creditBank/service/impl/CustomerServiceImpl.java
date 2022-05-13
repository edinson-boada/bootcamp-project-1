package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.CustomerTypeNotFoundException;
import com.nttdata.project.creditBank.mapper.CustomerMapper;
import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.model.api.CustomerProductsResponse;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Mono<Customer> getCustomer(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<CustomerProductsResponse> getCustomerProducts(String id) {
    return customerRepository
        .findById(id)
        .map(customerMapper::toCustomerProductsResponse);
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
    public Mono<Customer> deleteCustomer(String id) {
        return customerRepository.findById(id)
                .flatMap(deletedCustomer -> customerRepository.delete(deletedCustomer)
                        .then(Mono.just(deletedCustomer)));
    }

    @Override
    public Flux<Customer> getCustomerBootCoinSeller(boolean isBootCoinSeller) {
        return customerRepository.findByBootCoinSeller(isBootCoinSeller);
    }

    public void validateCustomerType(Customer customer) {
        try {
            CustomerType.valueOf(customer.getCustomerType());
        } catch (IllegalArgumentException e) {
            throw new CustomerTypeNotFoundException("Customer type must be PERSONAL or BUSINESS.");
        }
    }
}
