package com.nttdata.project01.creditBank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingAccount {
    private float commission;
    private int movements;
    private float balance;
}
