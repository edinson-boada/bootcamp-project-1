package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.DepositAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepositAccountRepository extends MongoRepository<DepositAccount, String> {
    boolean existsByCustomerId(String customerId);
}
