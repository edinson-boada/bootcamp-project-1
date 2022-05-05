package com.nttdata.project.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "debit_card_transactions")
public class DebitCardTransaction {
    @Id
    private String id;
    private String type;
    @Field(name = "customer_id")
    private String customerId;
    private float amount;
    @Field(name = "account")
    @DocumentReference
    private Account account;
}
