package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardService {
    Mono<CreditCard> getCreditCard(String id);
    Flux<CreditCard> getAllCreditCards();
    Mono<CreditCard> addCreditCard(CreditCard creditCard);
    Mono<CreditCard> updateCreditCard(CreditCard creditCard, String id);
    void updateAccountBalance(String creditCardId, float amount, String transactionType);
    void deleteCreditCard(String id);
}
