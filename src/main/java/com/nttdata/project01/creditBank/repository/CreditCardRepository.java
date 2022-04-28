package com.nttdata.project01.creditBank.repository;

import com.nttdata.project01.creditBank.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

}
