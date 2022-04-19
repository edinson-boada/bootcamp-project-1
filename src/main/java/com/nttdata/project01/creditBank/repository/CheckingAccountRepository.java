package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.CheckingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckingAccountRepository extends MongoRepository<CheckingAccount, String> {
}
