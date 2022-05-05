package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
    Mono<DebitCard> getDebitCard(String id);
    Flux<DebitCard> getAllDebitCards();
    Mono<DebitCard> addDebitCard(DebitCard debitCard);
    Mono<DebitCard> updateDebitCard(DebitCard debitCard, String id);
    //void updateAccountBalance(String DebitCardId, float amount, String transactionType);
    void deleteDebitCard(String id);
}
