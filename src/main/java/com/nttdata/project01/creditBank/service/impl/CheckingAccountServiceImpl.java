package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.model.CheckingAccount;
import com.nttdata.project01.creditBank.repository.CheckingAccountRepository;
import com.nttdata.project01.creditBank.service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Override
    public Optional<CheckingAccount> getCheckingAccount(String id) {
        return checkingAccountRepository.findById(id);
    }

    @Override
    public List<CheckingAccount> getAllCheckingAccount() {
        return checkingAccountRepository.findAll();
    }

    @Override
    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
        return checkingAccountRepository.save(checkingAccount);
    }

    @Override
    public CheckingAccount updateCheckingAccount(CheckingAccount checkingAccount, String id) {
        return null;
    }

    @Override
    public void deleteCheckingAccount(String id) {
        checkingAccountRepository.deleteById(id);
    }
}
