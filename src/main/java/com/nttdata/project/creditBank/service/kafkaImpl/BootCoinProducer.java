package com.nttdata.project.creditBank.service.kafkaImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BootCoinProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void publish(String personPurchaseOrder, String topic) {
    kafkaTemplate.send(topic, personPurchaseOrder);
  }
}
