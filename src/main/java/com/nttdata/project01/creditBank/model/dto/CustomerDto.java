package com.nttdata.project01.creditBank.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String type;
    private int creditsQuantity;
    private float creditCardBalance;
}
