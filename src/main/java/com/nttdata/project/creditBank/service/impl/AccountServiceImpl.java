package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.AccountRestrictionsException;
import com.nttdata.project.creditBank.exception.AccountTypeNotFoundException;
import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.Account;
import com.nttdata.project.creditBank.repository.AccountRepository;
import com.nttdata.project.creditBank.repository.CustomerRepository;
import com.nttdata.project.creditBank.service.AccountService;
import com.nttdata.project.creditBank.strategy.AccountType;
import com.nttdata.project.creditBank.strategy.CustomerType;
import com.nttdata.project.creditBank.strategy.DebitCardTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Account> getAccount(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> addAccount(Account account) {
        validateTypeAccount(account);

        if(account.getCustomers().size() == 1)
            validateOneCustomerAccountRestriction(account);
        else
            validateManyCustomersAccountRestriction(account);

        account.getCustomers().forEach(c -> {
            customerRepository.findById(c.getId()).doOnNext(customer -> {
                List<Account> accounts = customer.getAccounts();
                accounts.add(account);
                customer.setAccounts(accounts);
                customerRepository.save(customer);
            });
        });
        return accountRepository.save(account);
    }

    @Override
    public Mono<Account> updateAccount(Account account, String id) {
        return null;
    }

    @Override
    public void updateAccountBalance(String accountId, float amount, String transactionType) {

        validateTransactionType(transactionType);
        Account account = accountRepository.findById(accountId).block();

        if (AccountType.valueOf(account.getType()).validateRemainingMovements(account.getMovements()))
            account.setMovements(account.getMovements() - 1);

        if (DebitCardTransactionType.valueOf(transactionType).validateBalance(amount, account))
            account.setBalance(DebitCardTransactionType.valueOf(transactionType).calculateBalance(amount, account.getBalance()));

        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public boolean validateBalance(Account account, float amount) {
        Optional.of(account)
                .filter(a -> a.getBalance() - amount >= 0)
                .orElseThrow(() -> new AccountRestrictionsException("You do not have enough balance."));
        return true;
    }

    public void validateTypeAccount(Account account) {
        try {
            AccountType.valueOf(account.getType());
        } catch (IllegalArgumentException e) {
            throw new AccountTypeNotFoundException("Account type must be SAVING, CURRENT or DEPOSIT.");
        }
    }

    public void validateTransactionType(String transaction) {
        try {
            DebitCardTransactionType.valueOf(transaction);
        } catch (IllegalArgumentException e) {
            throw new TransactionTypeNotFoundException("Transaction type must be DEPOSIT or WITHDRAWAL");
        }
    }

    public void validateOneCustomerAccountRestriction(Account account) {
        customerRepository.findById(account.getCustomers().get(0).getId())
                .filter(c ->
                    (c.getType().equals(CustomerType.PERSONAL.toString()) && c.getAccounts().size() == 0) ||
                            (c.getType().equals(CustomerType.BUSINESS.toString()) &&
                                    account.getType().equals(AccountType.CURRENT.toString())))
                .switchIfEmpty(
                        Mono.error(new AccountRestrictionsException(
                        "A personal customer can only have a maximum of one savings account, " +
                                "one checking account or deposit accounts and A business customer cannot have a savings or deposit account " +
                                "but can have multiple checking accounts")))
                .subscribe();
    }

    public void validateManyCustomersAccountRestriction(Account account) {
        Optional.of(account)
                .filter(a -> a.getCustomers()
                        .stream()
                        .filter(c -> !customerRepository.findById(c.getId()).block().getType().equals(CustomerType.BUSINESS.toString()))
                        .findAny()
                        .isEmpty() && a.getType().equals(AccountType.CURRENT.toString()))
                .orElseThrow(() -> new AccountRestrictionsException("A business customer cannot have a savings or deposit account " +
                        "but can have multiple current accounts and only them can have one or more holders"));
    }
}
