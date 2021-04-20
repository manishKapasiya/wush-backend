package com.wush.kafka.consumer.entity;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.ArrayList;
import java.util.List;

public class KafkaConsumerResponse {

    private List<ConsumerRecords> consumerRecords = new ArrayList<>();

    public List<ConsumerRecords> getConsumerRecords() {
        return consumerRecords;
    }

    public void setConsumerRecords(List<ConsumerRecords> consumerRecords) {
        this.consumerRecords = consumerRecords;
    }

}
