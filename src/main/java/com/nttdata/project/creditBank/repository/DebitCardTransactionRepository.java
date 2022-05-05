package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.DebitCardTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DebitCardTransactionRepository extends ReactiveMongoRepository<DebitCardTransaction, String> {
    Flux<DebitCardTransaction> findByCustomerId(String customerId);
}
