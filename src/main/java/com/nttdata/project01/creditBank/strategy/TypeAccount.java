package com.nttdata.project01.creditBank.strategy;


import com.nttdata.project01.creditBank.exception.EmpresarialAccountException;
import com.nttdata.project01.creditBank.exception.PersonalAccountException;
import com.nttdata.project01.creditBank.model.dto.CustomerDto;

import java.util.Optional;

public enum TypeAccount {
    Personal {
        @Override
        public void validateAccount(CustomerDto customerDto) {
            Optional.of(customerDto)
                    .filter(c ->
                            c.getSavingAccount() == null && c.getCheckingAccounts() == null && c.getDepositAccount() == null ||
                            c.getSavingAccount() != null && c.getCheckingAccounts() == null && c.getDepositAccount() == null ||
                            c.getSavingAccount() == null && c.getCheckingAccounts() != null && c.getCheckingAccounts().size() == 1 && c.getDepositAccount() == null ||
                            c.getSavingAccount() == null && c.getCheckingAccounts() == null && c.getDepositAccount() != null)
                    .orElseThrow(() -> new PersonalAccountException("Un cliente de tipo Personal solo puede tener " +
                            "un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo"));
        }
    },
    Empresarial {
        @Override
        public void validateAccount(CustomerDto customerDto) {
      Optional.of(customerDto)
          .filter(c ->
                  c.getSavingAccount() == null && c.getCheckingAccounts() == null && c.getDepositAccount() == null ||
                  c.getSavingAccount() == null && c.getCheckingAccounts().size() > 0 && c.getDepositAccount() == null)
          .orElseThrow(() -> new EmpresarialAccountException("Un cliente empresarial no puede tener una cuenta de ahorro o " +
                  "de plazo fijo pero sí múltiples cuentas corrientes"));
        }
    };

    public abstract void validateAccount(CustomerDto customer);
}
