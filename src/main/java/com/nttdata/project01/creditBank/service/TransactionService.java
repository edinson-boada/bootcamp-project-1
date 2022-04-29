package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<Transaction> getTransaction(String id);
    Flux<Transaction> getAllTransactions();
    Mono<Transaction> addTransaction(Transaction transaction);
    Mono<Transaction> updateTransaction(Transaction transaction, String id);
    void deleteTransaction(String id);
    Flux<Transaction> getTransactionsByCustomerId(String customerId);
}
