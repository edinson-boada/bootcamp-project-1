package com.nttdata.project.creditBank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String type;
    private float commission;
    private int movements;
    private float balance;
    @Field(name = "customer_ids")
    private List<Customer> customers;
    @Field(name = "debit_card")
    @DocumentReference
    private DebitCard debitCard;
}
