package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

}
