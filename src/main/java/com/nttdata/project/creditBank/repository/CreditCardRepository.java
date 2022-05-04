package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {

}
