package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="cheking_accounts")
public class ChekingAccount {
    @Id
    private String id;
    private float commission;
    private int movements;
    private float balance;
}
