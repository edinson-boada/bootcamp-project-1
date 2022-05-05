package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.DebitCardTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardTransactionService {
    Mono<DebitCardTransaction> getTransaction(String id);
    Flux<DebitCardTransaction> getAllTransactions();
    Mono<DebitCardTransaction> addTransaction(DebitCardTransaction debitCardTransaction);
    Mono<DebitCardTransaction> updateTransaction(DebitCardTransaction debitCardTransaction, String id);
    void deleteTransaction(String id);
    Flux<DebitCardTransaction> getTransactionsByCustomerId(String customerId);
}
