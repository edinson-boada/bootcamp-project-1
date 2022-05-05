package com.nttdata.project.creditBank.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private String type;
    private float commission;
    private int movements;
    private float balance;
}
