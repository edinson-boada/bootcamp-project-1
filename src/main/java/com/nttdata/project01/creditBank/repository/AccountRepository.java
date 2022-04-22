package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

}
