package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.exception.PersonalAccountException;
import com.nttdata.project01.creditBank.model.SavingAccount;
import com.nttdata.project01.creditBank.repository.SavingAccountRepository;
import com.nttdata.project01.creditBank.service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Override
    public Optional<SavingAccount> getSavingAccount(String id) {
        return savingAccountRepository.findById(id);
    }

    @Override
    public List<SavingAccount> getAllSavingAccount() {
        return savingAccountRepository.findAll();
    }

    @Override
    public SavingAccount addSavingAccount(SavingAccount savingAccount) {
     //   validateBusinessCustomerAccountRestriction(savingAccount);
     //   validatePersonalCustomerAccountRestriction(savingAccount);
        return savingAccountRepository.save(savingAccount);
    }

    @Override
    public SavingAccount updateSavingAccount(SavingAccount savingAccount, String id) {
        return null;
    }

    @Override
    public void deleteSavingAccount(String id) {
        savingAccountRepository.deleteById(id);
    }

    private void validatePersonalCustomerAccountRestriction(SavingAccount savingAccount) {
        Optional.of(savingAccount)
                .filter(sa ->
                        !savingAccountRepository.existsByCustomerId(sa.getCustomer().getId()) &&
                                savingAccountRepository.findById(sa.getCustomer().getId()).get().getCustomer().getType().equals("PERSONAL"))
                .orElseThrow(() -> new PersonalAccountException("A personal customer can only have a maximum of one savings account, " +
                        "one checking account or deposit accounts"));
    }

    private void validateBusinessCustomerAccountRestriction(SavingAccount savingAccount) {
        Optional.of(savingAccount)
                .filter(sa -> savingAccountRepository.findById(sa.getCustomer().getId()).get().getCustomer().getType().equals("BUSINESS"))
                .orElseThrow(() -> new PersonalAccountException("A business customer cannot have a savings or deposit account " +
                        "but can have multiple checking accounts"));
    }
}
