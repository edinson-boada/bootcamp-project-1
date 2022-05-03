package com.nttdata.project.creditBank.strategy;

import com.nttdata.project.creditBank.exception.NoMovementsRemainingException;

import java.util.Optional;

public enum AccountType {
    SAVING {
        @Override
        public boolean validateRemainingMovements(int movements) {
            Optional.of(movements)
                    .filter(m -> m - 1 >= 0)
                    .orElseThrow(() -> new NoMovementsRemainingException("You have no more movements left in this account."));
            return true;
        }
    },
    CURRENT {
        @Override
        public boolean validateRemainingMovements(int movements) {
            return false;
        }
    },
    DEPOSIT {
        @Override
        public boolean validateRemainingMovements(int movements) {
            Optional.of(movements)
                    .filter(m -> m - 1 >= 0)
                    .orElseThrow(() -> new NoMovementsRemainingException("You have no more movements left in this account."));
            return true;
        }
    };

    public abstract boolean validateRemainingMovements(int movements);
}
