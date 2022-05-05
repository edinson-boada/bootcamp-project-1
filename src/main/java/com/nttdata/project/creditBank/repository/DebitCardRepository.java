package com.nttdata.project.creditBank.repository;

import com.nttdata.project.creditBank.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
}
