package com.nttdata.project.creditBank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Customer customer;
}
