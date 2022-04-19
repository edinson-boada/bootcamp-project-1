package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.CheckingAccount;

import java.util.List;
import java.util.Optional;

public interface CheckingAccountService {
    Optional<CheckingAccount> getCheckingAccount(String id);
    List<CheckingAccount> getAllCheckingAccount();
    CheckingAccount addCheckingAccount(CheckingAccount checkingAccount);
    CheckingAccount updateCheckingAccount(CheckingAccount checkingAccount, String id);
    void deleteCheckingAccount(String id);
}
