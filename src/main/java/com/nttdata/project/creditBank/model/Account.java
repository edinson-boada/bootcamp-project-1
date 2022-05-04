package com.nttdata.project.creditBank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String type;
    private float commission;
    private int movements;
    private float balance;
    @Field(name = "customer_ids")
    @DocumentReference
    private List<Customer> customers;
}
