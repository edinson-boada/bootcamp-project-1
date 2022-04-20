package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.CheckingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CheckingAccountService {
    Mono<CheckingAccount> getCheckingAccount(String id);
    Flux<CheckingAccount> getAllCheckingAccount();
    Mono<CheckingAccount> addCheckingAccount(CheckingAccount checkingAccount);
    Mono<CheckingAccount> updateCheckingAccount(CheckingAccount checkingAccount, String id);
    void deleteCheckingAccount(String id);
}
