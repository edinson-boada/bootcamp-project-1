package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.Transaction;
import com.nttdata.project.creditBank.service.TransactionService;
import com.nttdata.project.creditBank.repository.TransactionRepository;
import com.nttdata.project.creditBank.strategy.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Mono<Transaction> getTransaction(String id) {
        return Mono.just(transactionRepository.findById(id).get());
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return Flux.fromIterable(transactionRepository.findAll());
    }

    @Override
    public Mono<Transaction> addTransaction(Transaction transaction) {
        validateTransactionType(transaction);
        return Mono.just(transactionRepository.save(transaction));
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction Transaction, String id) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Flux<Transaction> getTransactionsByCustomerId(String customerId) {
        return Flux.fromIterable(transactionRepository.findByCustomerId(customerId));
    }

    public void validateTransactionType(Transaction transaction) {
        try {
            TransactionType.valueOf(transaction.getType());
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Transaction type must be DEPOSIT, WITHDRAWAL or PAYMENT.");
        }
    }
}
