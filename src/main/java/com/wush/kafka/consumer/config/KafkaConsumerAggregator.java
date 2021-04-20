package com.wush.kafka.consumer.config;

import com.wush.kafka.consumer.connector.IKafkaConsumerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("com.wush.consumers.hoolihan.KafkaConsumerAggregator")
public class KafkaConsumerAggregator {

    @Autowired
    private List<IKafkaConsumerConfiguration> consumerConfigs;

    public List<IKafkaConsumerConfiguration> getConsumerConfigs() {
        return consumerConfigs;
    }

    public void setConsumerConfigs(List<IKafkaConsumerConfiguration> consumerConfigs) {
        this.consumerConfigs = consumerConfigs;
    }
}
