package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.Transaction;
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
