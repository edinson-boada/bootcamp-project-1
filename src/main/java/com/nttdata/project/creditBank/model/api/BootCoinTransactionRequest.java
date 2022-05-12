package com.nttdata.project.creditBank.model.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootCoinTransactionRequest {
    private String personId;
    private String paymentMethod;
    private double amount;
}
