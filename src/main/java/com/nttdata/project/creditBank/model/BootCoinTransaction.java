package com.nttdata.project.creditBank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "boot_coin_transactions")
public class BootCoinTransaction {
    @Id
    private String id;
    private String paymentMethod;
    private int bootCoinQuantity;
    private String customerId;
    private String personId;
    private LocalDateTime localDateTime;
}
