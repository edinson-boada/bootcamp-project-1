package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.model.Transaction;
import com.nttdata.project01.creditBank.repository.TransactionRepository;
import com.nttdata.project01.creditBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository TransactionRepository;

    @Override
    public Mono<Transaction> getTransaction(String id) {
        return Mono.just(TransactionRepository.findById(id).get());
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return Flux.fromIterable(TransactionRepository.findAll());
    }

    @Override
    public Mono<Transaction> addTransaction(Transaction Transaction) {
        return Mono.just(TransactionRepository.save(Transaction));
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction Transaction, String id) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {
        TransactionRepository.deleteById(id);
    }
}
