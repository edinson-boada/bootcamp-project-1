package com.nttdata.project01.creditBank.service.impl;

import com.nttdata.project01.creditBank.model.DepositAccount;
import com.nttdata.project01.creditBank.repository.DepositAccountRepository;
import com.nttdata.project01.creditBank.service.DepositAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositAccountServiceImpl implements DepositAccountService {

    @Autowired
    private DepositAccountRepository depositAccountRepository;

    @Override
    public Optional<DepositAccount> getDepositAccount(String id) {
        return depositAccountRepository.findById(id);
    }

    @Override
    public List<DepositAccount> getAllDepositAccount() {
        return depositAccountRepository.findAll();
    }

    @Override
    public DepositAccount addDepositAccount(DepositAccount depositAccount) {
        return depositAccountRepository.save(depositAccount);
    }

    @Override
    public DepositAccount updateDepositAccount(DepositAccount depositAccount, String id) {
        return null;
    }

    @Override
    public void deleteDepositAccount(String id) {
        depositAccountRepository.deleteById(id);
    }
}
