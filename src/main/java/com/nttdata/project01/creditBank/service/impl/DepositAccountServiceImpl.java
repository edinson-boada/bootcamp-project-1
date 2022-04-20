package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.PersonalAccountException;
import com.nttdata.project01.creditBank.model.DepositAccount;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.repository.DepositAccountRepository;
import com.nttdata.project01.creditBank.service.DepositAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DepositAccountServiceImpl implements DepositAccountService {

    @Autowired
    private DepositAccountRepository depositAccountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<DepositAccount> getDepositAccount(String id) {
        return Mono.just(depositAccountRepository.findById(id).get());
    }

    @Override
    public Flux<DepositAccount> getAllDepositAccount() {
        return Flux.fromIterable(depositAccountRepository.findAll());
    }

    @Override
    public Mono<DepositAccount> addDepositAccount(DepositAccount depositAccount) {
        validateBusinessCustomerAccountRestriction(depositAccount);
        validatePersonalCustomerAccountRestriction(depositAccount);
        return Mono.just(depositAccountRepository.save(depositAccount));
    }

    @Override
    public Mono<DepositAccount> updateDepositAccount(DepositAccount depositAccount, String id) {
        return null;
    }

    @Override
    public void deleteDepositAccount(String id) {
        depositAccountRepository.deleteById(id);
    }

    public void validatePersonalCustomerAccountRestriction(DepositAccount depositAccount) {
        Optional.of(depositAccount)
                .filter(sa -> !depositAccountRepository.existsByCustomerId(sa.getCustomer().getId()))
                .orElseThrow(() -> new PersonalAccountException("A personal customer can only have a maximum of one savings account, " +
                        "one checking account or deposit accounts"));
    }

    public void validateBusinessCustomerAccountRestriction(DepositAccount savingAccount) {
        Optional.of(savingAccount)
                .filter(sa -> !customerRepository.findById(sa.getCustomer().getId()).get().getType().equals("BUSINESS"))
                .orElseThrow(() -> new PersonalAccountException("A business customer cannot have a savings or deposit account " +
                        "but can have multiple checking accounts"));
    }
}
