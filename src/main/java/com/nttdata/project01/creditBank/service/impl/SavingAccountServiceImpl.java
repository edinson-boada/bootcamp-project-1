package com.nttdata.project01.creditBank.service.impl;

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
}
