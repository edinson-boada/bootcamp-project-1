package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@Document(collection="customers")
public class Customer {
    @Id
    private String id;
    private String type;

    //Assets
    private int creditsQuantity;
    private float creditCardBalance;

    //Liabilities
    @DocumentReference
    private SavingAccount savingAccount;
    @DocumentReference
    private List<ChekingAccount> chekingAccount;
    @DocumentReference
    private DepositAccount depositAccount;
}
