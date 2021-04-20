package com.wush.kafka.consumer.connector;


import com.wush.kafka.consumer.entity.ConsumerConfigEntity;

import java.util.List;

public interface IKafkaConsumerConfiguration {
    List<ConsumerConfigEntity> getConsumerConfigEntities();
}
