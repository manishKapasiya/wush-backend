package com.wush.kafka.producer;

public interface IProducerConnector {

    void publish(String topic, String message, String acks);
}
