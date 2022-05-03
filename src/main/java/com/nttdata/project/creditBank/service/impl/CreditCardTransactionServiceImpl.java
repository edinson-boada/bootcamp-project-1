package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.strategy.CreditCardTransactionType;
import com.nttdata.project.creditBank.model.CreditCardTransaction;
import com.nttdata.project.creditBank.repository.CreditCardTransactionRepository;
import com.nttdata.project.creditBank.service.CreditCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CreditCardTransactionServiceImpl implements CreditCardTransactionService {

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Override
    public Mono<CreditCardTransaction> getCreditCardTransaction(String id) {
        return Mono.just(creditCardTransactionRepository.findById(id).get());
    }

    @Override
    public Flux<CreditCardTransaction> getAllCreditCardTransactions() {
        return Flux.fromIterable(creditCardTransactionRepository.findAll());
    }

    @Override
    public Mono<CreditCardTransaction> addCreditCardTransaction(CreditCardTransaction creditCardTransaction) {
        validateCreditCardTransactionType(creditCardTransaction);
        return Mono.just(creditCardTransactionRepository.save(creditCardTransaction));
    }

    @Override
    public Mono<CreditCardTransaction> updateCreditCardTransaction(CreditCardTransaction CreditCardTransaction, String id) {
        return null;
    }

    @Override
    public Flux<CreditCardTransaction> getAllCreditCardTransactionsByCustomer(String customerId) {
        return Flux.fromIterable(creditCardTransactionRepository.findByCreditCardCustomerId(customerId));
    }

    @Override
    public void deleteCreditCardTransaction(String id) {
        creditCardTransactionRepository.deleteById(id);
    }

    public void validateCreditCardTransactionType(CreditCardTransaction creditCardTransaction) {
        try {
            CreditCardTransactionType.valueOf(creditCardTransaction.getType());
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Credit card transaction type must be EXPENSE or PAYMENT.");
        }
    }
}
