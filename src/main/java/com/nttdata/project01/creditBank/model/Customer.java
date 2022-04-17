package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection="customers")
public class Customer {
    @Id
    private String id;
    private String type;

    //Assets
    @Field(name = "credits_quantity")
    private int creditsQuantity;
    @Field(name = "credit_card_balance")
    private float creditCardBalance;

    //Liabilities
    @Field(name = "saving_account")
    private SavingAccount savingAccount;
    @Field(name = "checking_accounts")
    private List<CheckingAccount> checkingAccounts;
    @Field(name = "deposit_account")
    private DepositAccount depositAccount;
}
