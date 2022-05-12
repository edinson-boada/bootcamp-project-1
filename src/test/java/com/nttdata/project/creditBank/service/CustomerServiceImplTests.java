package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.repository.CustomerRepository;
import com.nttdata.project.creditBank.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceImplTests {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private String id = "1";
    @Test
    public void getByIdTest(){
        Mono<Customer> customerMono = Mono.just(new Customer("1","PERSONAL","Edinson", "Boada", "72898058", "Mz. D Lt. 14"));
        when(customerRepository.findById(id)).thenReturn(customerMono);
        Mono<Customer> customer = customerService.getCustomer(id);
        StepVerifier
                .create(customer)
                .consumeNextWith(c -> {
                    Assertions.assertNotNull(c);
                    Assertions.assertEquals(c.getId(),id);
                })
                .verifyComplete();
    }

    @Test
    public void getAllTest(){
        Flux<Customer> customerFlux = Flux.just(
                new Customer("1","PERSONAL","Edinson", "Boada", "72898058", "Mz. D Lt. 14"),
                new Customer("2","BUSINESS","Paolo", "Cajo", "72898059", "Mz. D Lt. 15"));
        when(customerRepository.findAll()).thenReturn(customerFlux);
        StepVerifier.create(customerService.getAllCustomers())
                .expectSubscription()
                .expectNext(new Customer("1","PERSONAL","Edinson", "Boada", "72898058", "Mz. D Lt. 14"))
                .expectNext(new Customer("2","BUSINESS","Paolo", "Cajo", "72898059", "Mz. D Lt. 15"))
                .verifyComplete();
    }
}
