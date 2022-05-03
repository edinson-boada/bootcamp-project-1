package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

}
