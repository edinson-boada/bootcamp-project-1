package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class CreditCardTransaction {
    @Id
    private String id;
    private float amount;
    @Field(name = "credit_card")
    @DocumentReference
    private CreditCard creditCard;
}
