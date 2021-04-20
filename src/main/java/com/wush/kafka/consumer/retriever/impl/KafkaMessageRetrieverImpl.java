package com.wush.kafka.consumer.retriever.impl;

import com.wush.kafka.consumer.accessor.KafkaAccessor;
import com.wush.kafka.consumer.entity.KafkaConsumerResponse;
import com.wush.kafka.consumer.retriever.IKafkaMessageRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("com.wush.consumers.kafka.KafkaMessageRetrieverImpl")
public class KafkaMessageRetrieverImpl implements IKafkaMessageRetriever {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageRetrieverImpl.class);

    @Autowired
    private KafkaAccessor kafkaAccessor;

    @Override
    public KafkaConsumerResponse pullMessages(String topicName , int numberOfMessages, String groupId) {
        KafkaConsumerResponse kafkaConsumerResponse = null;
        kafkaConsumerResponse = kafkaAccessor.pullMessages(topicName, numberOfMessages, groupId);
        return kafkaConsumerResponse;
    }

    @Override
    public void acknowledgeMessages(String topicName, Set<Long> offsetIds) {
        kafkaAccessor.acknowledgeMessages(topicName,offsetIds);
    }
}
