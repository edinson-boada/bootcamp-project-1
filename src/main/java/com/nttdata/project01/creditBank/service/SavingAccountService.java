package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountService {
    Mono<SavingAccount> getSavingAccount(String id);
    Flux<SavingAccount> getAllSavingAccount();
    Mono<SavingAccount> addSavingAccount(SavingAccount savingAccount);
    Mono<SavingAccount> updateSavingAccount(SavingAccount savingAccount, String id);
    void deleteSavingAccount(String id);
}
