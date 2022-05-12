package com.nttdata.project.creditBank.service.kafkaImpl;

import com.google.gson.Gson;
import com.nttdata.project.creditBank.model.api.BootCoinTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BootCoinProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void publish(BootCoinTransactionRequest bootCoinTransactionRequest, String topic) {
    kafkaTemplate.send(topic, new Gson().toJson(bootCoinTransactionRequest));
  }
}
