package com.wush.supply.consumer;

import com.wush.kafka.consumer.service.IKafkaQueueMessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SupplyDemandConsumer implements IKafkaQueueMessageConsumer {
    @Override
    public Set<Long> consume(List<ConsumerRecords> events) {
        return null;
    }
}
