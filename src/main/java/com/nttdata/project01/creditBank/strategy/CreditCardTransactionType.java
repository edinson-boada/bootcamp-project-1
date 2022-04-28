package com.nttdata.project01.creditBank.strategy;

import com.nttdata.project01.creditBank.exception.AccountRestrictionsException;
import com.nttdata.project01.creditBank.model.CreditCard;

import java.util.Optional;

public enum CreditCardTransactionType {
    EXPENSE {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance - amount;
        }

        @Override
        public boolean validateBalance(float amount, CreditCard creditCard) {
            Optional.of(creditCard)
                    .filter(a -> a.getBalance() - amount >= 0)
                    .orElseThrow(() -> new AccountRestrictionsException("You do not have enough balance."));
            return true;
        }
    },
    PAYMENT {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance + amount;
        }

        @Override
        public boolean validateBalance(float amount, CreditCard creditCard) {
            return true;
        }
    };

    public abstract float calculateBalance(float amount, float balance);
    public abstract boolean validateBalance(float amount, CreditCard creditCard);
}
