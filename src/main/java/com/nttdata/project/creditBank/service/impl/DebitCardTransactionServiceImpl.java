package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.DebitCardTransaction;
import com.nttdata.project.creditBank.service.DebitCardTransactionService;
import com.nttdata.project.creditBank.repository.DebitCardTransactionRepository;
import com.nttdata.project.creditBank.strategy.DebitCardTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class DebitCardTransactionServiceImpl implements DebitCardTransactionService {
    @Autowired
    private DebitCardTransactionRepository debitCardTransactionRepository;

    @Override
    public Mono<DebitCardTransaction> getTransaction(String id) {
        return debitCardTransactionRepository.findById(id);
    }

    @Override
    public Flux<DebitCardTransaction> getAllTransactions() {
        return debitCardTransactionRepository.findAll();
    }

    @Override
    public Mono<DebitCardTransaction> addTransaction(DebitCardTransaction debitCardTransaction) {
        validateTransactionType(debitCardTransaction);
        return debitCardTransactionRepository.save(debitCardTransaction);
    }

    @Override
    public Mono<DebitCardTransaction> updateTransaction(DebitCardTransaction DebitCardTransaction, String id) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {
        debitCardTransactionRepository.deleteById(id);
    }

    @Override
    public Flux<DebitCardTransaction> getTransactionsByCustomerId(String customerId) {
        return debitCardTransactionRepository.findByCustomerId(customerId);
    }

    public void validateTransactionType(DebitCardTransaction debitCardTransaction) {
        try {
            DebitCardTransactionType.valueOf(debitCardTransaction.getType());
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Transaction type must be DEPOSIT, WITHDRAWAL or PAYMENT.");
        }
    }
}
