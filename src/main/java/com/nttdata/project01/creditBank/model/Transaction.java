package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String type;
    private float amount;
    @Field(name = "account")
    @DocumentReference
    private Account account;
}
