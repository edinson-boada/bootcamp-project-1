package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.TypeAccountNotFoundException;
import com.nttdata.project01.creditBank.mapper.CustomerMapper;
import com.nttdata.project01.creditBank.model.Customer;
import com.nttdata.project01.creditBank.model.dto.CustomerDto;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.service.CustomerService;
import com.nttdata.project01.creditBank.strategy.TypeAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Optional<Customer> getCustomer(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
//        validateTypeAccount(customerMapper.toDto(customer));
//        validateTypeAccountRestrictions(customerMapper.toDto(customer));
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, String id) {
        return null;
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    private void validateTypeAccountRestrictions(CustomerDto customerDto) {
        TypeAccount.valueOf(customerDto.getType()).validateAccount(customerDto);
    }

    private void validateTypeAccount(CustomerDto customerDto) {
        try {
            TypeAccount.valueOf(customerDto.getType());
        } catch (IllegalArgumentException e) {
            throw new TypeAccountNotFoundException("El tipo de cuenta debe ser Personal o Empresarial.");
        }
    }
}
