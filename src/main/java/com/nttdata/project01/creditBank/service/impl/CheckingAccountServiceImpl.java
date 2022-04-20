package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.model.CheckingAccount;
import com.nttdata.project01.creditBank.repository.CheckingAccountRepository;
import com.nttdata.project01.creditBank.service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CheckingAccountServiceImpl implements CheckingAccountService {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Override
    public Mono<CheckingAccount> getCheckingAccount(String id) {
        return Mono.just(checkingAccountRepository.findById(id).get());
    }

    @Override
    public Flux<CheckingAccount> getAllCheckingAccount() {
        return Flux.fromIterable(checkingAccountRepository.findAll());
    }

    @Override
    public Mono<CheckingAccount> addCheckingAccount(CheckingAccount checkingAccount) {
        return Mono.just(checkingAccountRepository.save(checkingAccount));
    }

    @Override
    public Mono<CheckingAccount> updateCheckingAccount(CheckingAccount checkingAccount, String id) {
        return null;
    }

    @Override
    public void deleteCheckingAccount(String id) {
        checkingAccountRepository.deleteById(id);
    }

    private void validatePersonalCustomerAccountRestriction(CheckingAccount checkingAccount) {
    }
}
