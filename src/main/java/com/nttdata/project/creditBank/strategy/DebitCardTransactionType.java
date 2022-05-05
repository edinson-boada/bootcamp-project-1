package com.nttdata.project.creditBank.strategy;

import com.nttdata.project.creditBank.exception.AccountRestrictionsException;
import com.nttdata.project.creditBank.model.Account;

import java.util.Optional;

public enum DebitCardTransactionType {
    DEPOSIT {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance + amount;
        }

        @Override
        public boolean validateBalance(float amount, Account account) {
            return true;
        }
    },
    WITHDRAWAL {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance - amount;
        }

        @Override
        public boolean validateBalance(float amount, Account account) {
            Optional.of(account)
                    .filter(a -> a.getBalance() - amount >= 0)
                    .orElseThrow(() -> new AccountRestrictionsException("You do not have enough balance."));
            return true;
        }
    };

    public abstract float calculateBalance(float amount, float balance);
    public abstract boolean validateBalance(float amount, Account account);
}
