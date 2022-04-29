package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.CreditCardTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardTransactionService {
    Mono<CreditCardTransaction> getCreditCardTransaction(String id);
    Flux<CreditCardTransaction> getAllCreditCardTransactions();
    Mono<CreditCardTransaction> addCreditCardTransaction(CreditCardTransaction creditCardTransaction);
    Mono<CreditCardTransaction> updateCreditCardTransaction(CreditCardTransaction creditCardTransaction, String id);
    Flux<CreditCardTransaction> getAllCreditCardTransactionsByCustomer(String customerId);
    void deleteCreditCardTransaction(String id);
}
