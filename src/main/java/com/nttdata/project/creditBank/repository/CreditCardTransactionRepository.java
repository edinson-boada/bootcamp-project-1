package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.CreditCardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CreditCardTransactionRepository extends MongoRepository<CreditCardTransaction, String> {
    List<CreditCardTransaction> findByCreditCardCustomerId(String customerId);
}
