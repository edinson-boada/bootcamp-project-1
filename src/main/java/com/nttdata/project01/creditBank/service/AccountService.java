package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> getAccount(String id);
    Flux<Account> getAllAccounts();
    Mono<Account> addAccount(Account account);
    Mono<Account> updateAccount(Account account, String id);
    void updateAccountBalance(String accountId, float amount, String transactionType);
    void deleteAccount(String id);
}
