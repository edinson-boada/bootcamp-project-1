package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="saving_accounts")
public class SavingAccount {
    @Id
    private String id;
    private int movements;
    private float balance;
}
