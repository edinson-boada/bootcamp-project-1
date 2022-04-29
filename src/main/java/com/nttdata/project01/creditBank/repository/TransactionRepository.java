package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByCustomerId(String customerId);
}
