package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.CreditCardTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditCardTransactionRepository extends ReactiveMongoRepository<CreditCardTransaction, String> {
    Flux<CreditCardTransaction> findByCreditCardCustomerId(String customerId);
}
