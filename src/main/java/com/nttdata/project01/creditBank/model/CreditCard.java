package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "credit_cards")
public class CreditCard {
    @Id
    private String id;
    @Field(name = "card_number")
    private String cardNumber;
    @Field(name = "expiration_date")
    private String expirationDate;
    private String CCI;
    private float balance;
    @DocumentReference
    private Customer customer;
}
