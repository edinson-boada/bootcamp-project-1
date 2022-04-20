package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.SavingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SavingAccountRepository extends MongoRepository<SavingAccount, String> {
    boolean existsByCustomerId(String customerId);
}
