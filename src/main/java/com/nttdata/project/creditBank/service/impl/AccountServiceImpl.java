package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.exception.AccountRestrictionsException;
import com.nttdata.project.creditBank.exception.AccountTypeNotFoundException;
import com.nttdata.project.creditBank.exception.TransactionTypeNotFoundException;
import com.nttdata.project.creditBank.model.Account;
import com.nttdata.project.creditBank.model.Customer;
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

import java.util.stream.Collectors;

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

    /*        accountRepository.findByCustomersIdIn(
            account.getCustomers().stream().map(customer -> customer.getId()).collect(Collectors.toList()));

    accountRepository.findByCustomersId()

    customerRepository.findById(account.getCustomers().get(0).getId())
            .filter(customer ->
                    customer.getType().equals(CustomerType.PERSONAL.toString()) && )

    return (Optional.ofNullable(account).isEmpty()
            ? accountRepository.save(account);*/

    return customerRepository
        .findByIdIn(
            account.getCustomers().stream()
                .map(currentCustomer -> currentCustomer.getId())
                .collect(Collectors.toList()))
        .flatMap(
            customer ->
                accountRepository
                    .findByCustomersId(customer.getId())
                    .count()
                    .filter(size -> validateType(customer, size, account))
                    .switchIfEmpty(Mono.error(new AccountRestrictionsException("gaxxx"))))
            .last()
        .flatMap(size -> accountRepository.save(account));

        /*
        return customerRepository.findById(account.getCustomers().get(0).getId())
                .flatMap(customer -> accountRepository.findByCustomersId(account.getCustomers().get(0).getId())
                        .count()
                        .filter(size -> validateType(customer, size, account))
                        .switchIfEmpty(Mono.error(new AccountRestrictionsException("ga"))))
                .flatMap(size -> accountRepository.save(account));
         */

        /*
        return customerRepository.findById(account.getCustomers().get(0).getId())
                .zipWith(accountRepository.findByCustomersId(account.getCustomers().get(0).getId())
                        .count())
                .filter(customerSizeTuple -> validateType(customerSizeTuple.getT1(), customerSizeTuple.getT2(), account))
                .switchIfEmpty(Mono.error(new AccountRestrictionsException("ga")))
                .flatMap(size -> accountRepository.save(account));
         */
    }



    private static Boolean validateType(Customer c, Long size, Account account) {
        return (c.getType().equals(CustomerType.PERSONAL.toString()) && size == 0) ||
                            (c.getType().equals(CustomerType.BUSINESS.toString()) &&
                                    account.getType().equals(AccountType.CURRENT.toString()));
    }

        public void validateOneCustomerAccountRestrictionx(Account account) {
  /*      Mono<Customer> fxCustomer = customerRepository.findById(account.getCustomers().get(0).getId());
        Flux<Account> fxAccounts = accountRepository.findByCustomersId(account.getCustomers().get(0).getId());




        fxCustomer.zipWith(Flux.just(fxCustomer), (customer, accounts) -> {
                    customer.getType() && accounts.()
        }*/

//                .filter(c ->
//                    (c.getType().equals(CustomerType.PERSONAL.toString()) && c.getAccounts().size() == 0) ||
//                            (c.getType().equals(CustomerType.BUSINESS.toString()) &&
//                                    account.getType().equals(AccountType.CURRENT.toString())))
//                .switchIfEmpty(
//                        Mono.error(new AccountRestrictionsException(
//                        "A personal customer can only have a maximum of one savings account, " +
//                                "one checking account or deposit accounts and A business customer cannot have a savings or deposit account " +
//                                "but can have multiple checking accounts")));
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

    public void validateBalance(Account account, float amount) {

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

    }

    public void validateManyCustomersAccountRestriction(Account account) {

    }
}
