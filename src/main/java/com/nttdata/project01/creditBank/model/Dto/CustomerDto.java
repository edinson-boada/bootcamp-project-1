package com.nttdata.project01.creditBank.model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String type;
    private int creditsQuantity;
    private float creditCardBalance;
    private SavingAccountDto savingAccount;
    private List<CheckingAccountDto> checkingAccounts;
    private DepositAccountDto depositAccount;
}
