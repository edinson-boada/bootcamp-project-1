package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomer(String id);
    List<Customer> getAllCustomers();
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer, String id);
    void deleteCustomer(String id);
}
