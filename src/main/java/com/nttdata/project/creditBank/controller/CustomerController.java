package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.model.api.CustomerProductsResponse;
import com.nttdata.project.creditBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Mono<Customer>> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Customer>> getCustomer(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<Flux<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Mono<CustomerProductsResponse>> getCustomerProducts(@PathVariable String id) {
        return ResponseEntity.ok().body(customerService.getCustomerProducts(id));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id)
                .map(r -> ResponseEntity.ok().<Void> build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Customer>> update(@PathVariable String id, @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(customer, id));
    }
}
