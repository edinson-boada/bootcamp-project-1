package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.CreditCard;
import com.nttdata.project.creditBank.service.CreditCardService;
import com.nttdata.project.creditBank.strategy.CreditCardTransactionType;
import com.nttdata.project.creditBank.model.Customer;
import com.nttdata.project.creditBank.repository.CreditCardRepository;
import com.nttdata.project.creditBank.repository.CustomerRepository;
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
        return Mono.just(creditCardRepository.findById(id).get());
    }

    @Override
    public Flux<CreditCard> getAllCreditCards() {
        return Flux.fromIterable(creditCardRepository.findAll());
    }

    @Override
    public Mono<CreditCard> addCreditCard(CreditCard creditCard) {
        Customer customer = customerRepository.findById(creditCard.getCustomer().getId()).get();
        customer.setCreditCard(creditCard);
        customerRepository.save(customer);
        return Mono.just(creditCardRepository.save(creditCard));
    }

    @Override
    public Mono<CreditCard> updateCreditCard(CreditCard creditCard, String id) {
        return null;
    }

    @Override
    public void updateAccountBalance(String creditCardId, float amount, String creditCardtransactionType) {

        validateCreditCardTransactionType(creditCardtransactionType);

        CreditCard creditCard = creditCardRepository.findById(creditCardId).get();

        if (CreditCardTransactionType.valueOf(creditCardtransactionType).validateBalance(amount, creditCard))
            creditCard.setBalance(CreditCardTransactionType.valueOf(creditCardtransactionType).calculateBalance(amount, creditCard.getBalance()));

        creditCardRepository.save(creditCard);
    }

    @Override
    public void deleteCreditCard(String id) {
        creditCardRepository.deleteById(id);
    }

    public void validateCreditCardTransactionType(String creditCardtransactionType) {
        try {
            CreditCardTransactionType.valueOf(creditCardtransactionType);
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Credit card transaction type must be EXPENSE or PAYMENT.");
        }
    }
}
