package com.nttdata.project01.creditBank.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingAccountDto {
    private float commission;
    private int movements;
    private float balance;
}
