package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.BootCoinTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BootCoinTransactionRepository extends ReactiveMongoRepository<BootCoinTransaction, String> {
}
