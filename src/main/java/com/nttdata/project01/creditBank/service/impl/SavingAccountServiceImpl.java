package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.PersonalAccountException;
import com.nttdata.project01.creditBank.model.SavingAccount;
import com.nttdata.project01.creditBank.repository.CustomerRepository;
import com.nttdata.project01.creditBank.repository.SavingAccountRepository;
import com.nttdata.project01.creditBank.service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Transactional
public class SavingAccountServiceImpl implements SavingAccountService {

  @Autowired private SavingAccountRepository savingAccountRepository;
  @Autowired private CustomerRepository customerRepository;

  @Override
  public Mono<SavingAccount> getSavingAccount(String id) {
    return Mono.just(savingAccountRepository.findById(id).get());
  }

  @Override
  public Flux<SavingAccount> getAllSavingAccount() {
    return Flux.fromIterable(savingAccountRepository.findAll());
  }

  @Override
  public Mono<SavingAccount> addSavingAccount(SavingAccount savingAccount) {
       validateBusinessCustomerAccountRestriction(savingAccount);
       validatePersonalCustomerAccountRestriction(savingAccount);
    return Mono.just(savingAccountRepository.save(savingAccount));
  }

  @Override
  public Mono<SavingAccount> updateSavingAccount(SavingAccount savingAccount, String id) {
    return null;
  }

  @Override
  public void deleteSavingAccount(String id) {
    savingAccountRepository.deleteById(id);
  }

  public void validatePersonalCustomerAccountRestriction(SavingAccount savingAccount) {
            Optional.of(savingAccount)
                    .filter(sa -> !savingAccountRepository.existsByCustomerId(sa.getCustomer().getId()))
                    .orElseThrow(() -> new PersonalAccountException("A personal customer can only have a maximum of one savings account, " +
                            "one checking account or deposit accounts"));
  }

  public void validateBusinessCustomerAccountRestriction(SavingAccount savingAccount) {
            Optional.of(savingAccount)
                    .filter(sa -> !customerRepository.findById(sa.getCustomer().getId()).get().getType().equals("BUSINESS"))
                    .orElseThrow(() -> new PersonalAccountException("A business customer cannot have a savings or deposit account " +
                            "but can have multiple checking accounts"));
  }
}
