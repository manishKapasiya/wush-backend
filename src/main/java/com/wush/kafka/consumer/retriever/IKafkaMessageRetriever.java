package com.wush.kafka.consumer.retriever;


import com.wush.kafka.consumer.entity.KafkaConsumerResponse;

import java.util.Set;

public interface IKafkaMessageRetriever {
    /**
     *
     * @param topicName topic name of the hoolihan on which the consumers are listening
     * @param numberOfMessages number of messages the hoolihan puller will fetch in one go
     * @return
     */
    KafkaConsumerResponse pullMessages(String topicName , int numberOfMessages, String groupId);

    /**
     *
     * @param topicName topic name of the hoolihan on which the consumers are listening
     * @param offsetIds the offsetIds which need to be acknowledged
     */
    void acknowledgeMessages(String topicName, Set<Long> offsetIds);

}
