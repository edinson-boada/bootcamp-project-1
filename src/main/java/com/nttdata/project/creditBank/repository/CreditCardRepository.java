package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

}
