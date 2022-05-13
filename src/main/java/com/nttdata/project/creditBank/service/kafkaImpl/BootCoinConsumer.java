package com.nttdata.project.creditBank.service.kafkaImpl;

import com.google.gson.Gson;
import com.nttdata.project.creditBank.model.BootCoin;
import com.nttdata.project.creditBank.model.BootCoinTransaction;
import com.nttdata.project.creditBank.model.api.BootCoinTransactionRequest;
import com.nttdata.project.creditBank.service.BootCoinTransactionService;
import com.nttdata.project.creditBank.service.CustomerService;
import com.nttdata.project.creditBank.util.KafkaUtils;
import com.nttdata.project.creditBank.util.OperatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BootCoinConsumer {

  @Autowired
  private BootCoinTransactionService bootCoinTransactionService;

  @Autowired
  private CustomerService customerService;

  @KafkaListener(topics = KafkaUtils.BOOT_COIN_KAFKA_TOPIC, groupId = KafkaUtils.KAFKA_GROUP_ID)
  public void consume(String bootCoinTransactionRequest) {
    Gson gson = new Gson();

    BootCoinTransactionRequest currentBootCoinTransactionRequest =
            gson.fromJson(bootCoinTransactionRequest, BootCoinTransactionRequest.class);

    customerService.getCustomerBootCoinSeller(true)
            .map(customer -> customer.getId())
            .collect(Collectors.toList())
            .flatMap(list -> bootCoinTransactionService
                    .addBootCoinTransaction(
                            BootCoinTransaction.builder()
                                    .id(UUID.randomUUID().toString())
                                    .paymentMethod(currentBootCoinTransactionRequest.getPaymentMethod())
                                    .bootCoinQuantity(OperatorUtils.getIntegerPart(currentBootCoinTransactionRequest.getAmount(), BootCoin.SELLING_RATE))
                                    .personId(currentBootCoinTransactionRequest.getPersonId())
                                    .customerId(OperatorUtils.randomGenerator(list))
                                    .localDateTime(LocalDateTime.now())
                                    .build()))
            .subscribe();
  }
}
