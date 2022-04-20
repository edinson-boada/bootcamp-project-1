package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.DepositAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositAccountService {
    Mono<DepositAccount> getDepositAccount(String id);
    Flux<DepositAccount> getAllDepositAccount();
    Mono<DepositAccount> addDepositAccount(DepositAccount depositAccount);
    Mono<DepositAccount> updateDepositAccount(DepositAccount depositAccount, String id);
    void deleteDepositAccount(String id);
}
