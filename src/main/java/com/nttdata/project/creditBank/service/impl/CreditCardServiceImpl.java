package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.CreditCard;
import com.nttdata.project.creditBank.repository.CreditCardRepository;
import com.nttdata.project.creditBank.repository.CustomerRepository;
import com.nttdata.project.creditBank.service.CreditCardService;
import com.nttdata.project.creditBank.strategy.CreditCardTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<CreditCard> getCreditCard(String id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    @Override
    public Mono<CreditCard> addCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Mono<CreditCard> updateCreditCard(CreditCard creditCard, String id) {
        return null;
    }

    @Override
    public void updateAccountBalance(String creditCardId, float amount, String creditCardtransactionType) {
    }

    @Override
    public void deleteCreditCard(String id) {
        creditCardRepository.deleteById(id);
    }

    public void validateCreditCardTransactionType(String creditCardTransactionType) {
        try {
            CreditCardTransactionType.valueOf(creditCardTransactionType);
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Credit card transaction type must be EXPENSE or PAYMENT.");
        }
    }
}
