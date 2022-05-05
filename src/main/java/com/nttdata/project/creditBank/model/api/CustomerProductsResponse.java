package com.nttdata.project.creditBank.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProductsResponse {
    private String type;
    private String names;
    private String surnames;
    private String docNumber;
    private int age;
    private List<AccountResponse> accounts;
    private CreditCardResponse creditCard;
}
