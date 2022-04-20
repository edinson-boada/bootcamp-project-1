package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String type;
    private String names;
    private String surnames;
    @Field(name = "doc_number")
    private String docNumber;
    private int age;
    @Field(name = "credits_quantity")
    private int creditsQuantity;
    @Field(name = "credit_card_balance")
    private float creditCardBalance;

}
