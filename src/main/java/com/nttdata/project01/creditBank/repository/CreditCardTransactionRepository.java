package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.CreditCardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardTransactionRepository extends MongoRepository<CreditCardTransaction, String> {
}
