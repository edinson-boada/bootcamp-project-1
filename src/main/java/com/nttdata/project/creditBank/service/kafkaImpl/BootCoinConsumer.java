package com.nttdata.project.creditBank.service.kafkaImpl;

import com.google.gson.Gson;
import com.nttdata.project.creditBank.model.BootCoinTransaction;
import com.nttdata.project.creditBank.model.Person;
import com.nttdata.project.creditBank.service.BootCoinTransactionService;
import com.nttdata.project.creditBank.util.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BootCoinConsumer {

  @Autowired
  private BootCoinTransactionService bootCoinTransactionService;

  @KafkaListener(topics = KafkaUtils.BOOT_COIN_KAFKA_TOPIC, groupId = KafkaUtils.KAFKA_GROUP_ID)
  public void consume(String purchaseOrder) {
    Gson gson = new Gson();

    bootCoinTransactionService
        .addBootCoinTransaction(
            BootCoinTransaction.builder()
                .id(UUID.randomUUID().toString())
                .paymentMethod("TRANSFER")
                .amount(230)
                .personId(gson.fromJson(purchaseOrder, Person.class).getId())
                .customerId("1")
                .localDateTime(LocalDateTime.now())
                .build())
        .subscribe();
  }
}
