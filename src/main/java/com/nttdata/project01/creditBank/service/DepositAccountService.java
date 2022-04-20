package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.DepositAccount;

import java.util.List;
import java.util.Optional;

public interface DepositAccountService {
    Optional<DepositAccount> getDepositAccount(String id);
    List<DepositAccount> getAllDepositAccount();
    DepositAccount addDepositAccount(DepositAccount depositAccount);
    DepositAccount updateDepositAccount(DepositAccount depositAccount, String id);
    void deleteDepositAccount(String id);
}
