package com.wush.kafka.consumer.executor;


import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import com.wush.kafka.consumer.exception.KafkaNoEventsException;

public interface IKafkaConsumerExecutor {

    public void execute(ConsumerConfigEntity consumerConfigEntity) throws KafkaNoEventsException;
}
