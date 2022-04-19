package com.nttdata.project01.creditBank.service;

import com.nttdata.project01.creditBank.model.SavingAccount;

import java.util.List;
import java.util.Optional;

public interface SavingAccountService {
    Optional<SavingAccount> getSavingAccount(String id);
    List<SavingAccount> getAllSavingAccount();
    SavingAccount addSavingAccount(SavingAccount savingAccount);
    SavingAccount updateSavingAccount(SavingAccount savingAccount, String id);
    void deleteSavingAccount(String id);
}
