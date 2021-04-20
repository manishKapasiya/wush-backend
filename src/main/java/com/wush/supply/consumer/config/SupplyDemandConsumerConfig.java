package com.wush.supply.consumer.config;

import com.wush.kafka.consumer.connector.IKafkaConsumerConfiguration;
import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "com.wush.supply.consumers.kafka.queue")
public class SupplyDemandConsumerConfig implements IKafkaConsumerConfiguration {

    private List<ConsumerConfigEntity> consumerConfigEntities = new ArrayList<>();

    public List<ConsumerConfigEntity> getConsumerConfigEntities() {
        return consumerConfigEntities;
    }

    public void setConsumerConfigEntities(List<ConsumerConfigEntity> consumerConfigEntities) {
        this.consumerConfigEntities = consumerConfigEntities;
    }

}

