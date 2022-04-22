package com.nttdata.project01.creditBank.strategy;

public enum TransactionType {
    DEPOSIT {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance + amount;
        }
    },
    WITHDRAWAL {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance - amount;
        }
    },
    PAYMENT {
        @Override
        public float calculateBalance(float amount, float balance) {
            return balance - amount;
        }
    };

    public abstract float calculateBalance(float amount, float balance);
}
