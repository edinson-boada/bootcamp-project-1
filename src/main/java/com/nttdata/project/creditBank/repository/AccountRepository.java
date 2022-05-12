package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findByCustomersId(String id);
}
