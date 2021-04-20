package com.wush.kafka.consumer.service;


import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.List;
import java.util.Set;

public interface IKafkaQueueMessageConsumer {
    /**
     * provides a consume method which will be used to provide the hoolihan consumer implementation on messages
     * @param events events which need to be consumed
     * @return return the list of offset ids which have been consumed with success
     */
    Set<Long> consume(List<ConsumerRecords> events);
}
