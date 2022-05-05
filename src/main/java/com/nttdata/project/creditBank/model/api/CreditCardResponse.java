package com.nttdata.project.creditBank.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.project.creditBank.model.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardResponse {
    private String cardNumber;
    private String expirationDate;
    private String CCI;
    private float balance;
    private Customer customer;
}
