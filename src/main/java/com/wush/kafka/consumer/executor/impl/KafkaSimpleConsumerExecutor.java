package com.wush.kafka.consumer.executor.impl;

import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import com.wush.kafka.consumer.entity.KafkaConsumerResponse;
import com.wush.kafka.consumer.exception.KafkaNoEventsException;
import com.wush.kafka.consumer.executor.IKafkaConsumerExecutor;
import com.wush.kafka.consumer.retriever.IKafkaMessageRetriever;
import com.wush.kafka.consumer.service.IKafkaQueueMessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component("com.wush.consumer.kafka.KafkaSimpleConsumerExecutor")
public class KafkaSimpleConsumerExecutor implements IKafkaConsumerExecutor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSimpleConsumerExecutor.class);

    private ApplicationContext applicationContext;

    @Autowired
    private IKafkaMessageRetriever kafkaMessageRetriever;

    @Override
    public void execute(ConsumerConfigEntity consumerConfigEntity) {
        if (consumerConfigEntity==null){
            logger.error("Cannot execute null consumer configuration");
            return;
        }
        KafkaConsumerResponse retrieveRoqEventsResponse = retrieveRoqEvents(consumerConfigEntity);
        if (retrieveRoqEventsResponse == null || retrieveRoqEventsResponse.getConsumerRecords().isEmpty()){
            logger.info("No Events for roq {}",consumerConfigEntity.getQualifierName());
            throw new KafkaNoEventsException();
        }
        IKafkaQueueMessageConsumer messageConsumer = getConsumerBean(consumerConfigEntity.getExecutorBeanName());
        if (messageConsumer==null) {
            logger.error("Invalid Consumer Bean for topic {}",consumerConfigEntity.getTopicName());
            return;
        }
        Set<Long> messagesAckList = messageConsumer.consume(retrieveRoqEventsResponse.getConsumerRecords());
        if (!CollectionUtils.isEmpty(messagesAckList)) {
            kafkaMessageRetriever.acknowledgeMessages(consumerConfigEntity.getTopicName()
                    , messagesAckList);
        }

    }

    private KafkaConsumerResponse retrieveRoqEvents(ConsumerConfigEntity consumerConfigEntity){
        return kafkaMessageRetriever.pullMessages(
                consumerConfigEntity.getTopicName(),
                consumerConfigEntity.getCountOfMessagesPolled(), consumerConfigEntity.getGroupId());
    }

    private IKafkaQueueMessageConsumer getConsumerBean(String executorBeanName){
        return applicationContext.getBean(executorBeanName, IKafkaQueueMessageConsumer.class);
    }

}
