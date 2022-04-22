package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.TypeAccountNotFoundException;
import com.nttdata.project01.creditBank.model.Account;
import com.nttdata.project01.creditBank.model.Customer;
import com.nttdata.project01.creditBank.repository.AccountRepository;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.service.AccountService;
import com.nttdata.project01.creditBank.strategy.AccountType;
import com.nttdata.project01.creditBank.strategy.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Account> getAccount(String id) {
        return Mono.just(accountRepository.findById(id).get());
    }

    @Override
    public Flux<Account> getAllAccounts() {
        return Flux.fromIterable(accountRepository.findAll());
    }

    @Override
    public Mono<Account> addAccount(Account account) {
        for (Customer c : account.getCustomers()) {
            Customer customer = customerRepository.findById(c.getId()).get();
            List<Account> accounts = customer.getAccounts();
            accounts.add(account);
            customer.setAccounts(accounts);
            customerRepository.save(customer);
        }
        return Mono.just(accountRepository.save(account));
    }

    @Override
    public Mono<Account> updateAccount(Account account, String id) {
        return null;
    }

    @Override
    public void updateAccountBalance(String accountId, float amount, String transactionType) {
        Account account = accountRepository.findById(accountId).get();
        account.setBalance(TransactionType.valueOf(transactionType).calculateBalance(amount, account.getBalance()));
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    private void validateTypeAccount(Account account) {
        try {
            AccountType.valueOf(account.getType());
        } catch (IllegalArgumentException e) {
            throw new TypeAccountNotFoundException("Account type must be SAVING, CURRENT or DEPOSIT.");
        }
    }
}
