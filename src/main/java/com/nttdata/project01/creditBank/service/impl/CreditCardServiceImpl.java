package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.model.Account;
import com.nttdata.project01.creditBank.model.CreditCard;
import com.nttdata.project01.creditBank.model.Customer;
import com.nttdata.project01.creditBank.repository.CreditCardRepository;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.service.CreditCardService;
import com.nttdata.project01.creditBank.strategy.AccountType;
import com.nttdata.project01.creditBank.strategy.TransactionType;
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
    public void updateAccountBalance(String creditCardId, float amount, String transactionType) {

    }

    @Override
    public void deleteCreditCard(String id) {
        creditCardRepository.deleteById(id);
    }
}
