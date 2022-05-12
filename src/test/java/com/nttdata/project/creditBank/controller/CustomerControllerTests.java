package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
class CustomerControllerTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private CustomerService customerService;

	@Test
	public void createCustomer(){
		Customer customer = new Customer("1","PERSONAL","Edinson", "Boada", "72898058", 12);
		Mono<Customer> customerMono = Mono.just(new Customer("1","PERSONAL","Edinson", "Boada", "72898058", 12));
		when(customerService.addCustomer(customer)).thenReturn(customerMono);
		webTestClient.post().uri("/api/v1/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(customerMono),Customer.class)
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	public void getAllCustomer(){
		var customerFlux = Flux.just(
				new Customer("1","PERSONAL","Edinson", "Boada", "72898058", 12),
				new Customer("2","BUSINESS","Paolo", "Cajo", "72898059", 13));
		when(customerService.getAllCustomers()).thenReturn(customerFlux);
		var responseBody = webTestClient.get().uri("/api/v1/customers")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customer.class)
				.getResponseBody();
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new Customer("1","PERSONAL","Edinson", "Boada", "72898058", 12))
				.expectNext(new Customer("2","BUSINESS","Paolo", "Cajo", "72898059", 13))
				.verifyComplete();
	}

	@Test
	public void getCustomerById(){
		Mono<Customer> customerMono = Mono.just(new Customer("1","PERSONAL","Edinson", "Boada", "72898058", 12));
		when(customerService.getCustomer(anyString())).thenReturn(customerMono);
		var customerResp = webTestClient.get().uri("/api/v1/customers/1")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customer.class)
				.getResponseBody();

		StepVerifier.create(customerMono)
				.expectSubscription()
				.expectNextMatches(c -> c.getDocNumber().equals("72898058"))
				.verifyComplete();
	}

	@Test
	public void deleteTest(){
//		given(employeeService.deleteEmp(any())).willReturn(Mono.empty());
		given(customerService.deleteCustomer(anyString())).willReturn(Mono.empty());
		webTestClient.delete().uri("/api/v1/delete/1")
				.exchange()
				.expectStatus().isNotFound();
	}
}
