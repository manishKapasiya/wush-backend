package com.wush.kafka.consumer.executor.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import com.wush.kafka.consumer.executor.IKafkaConsumerExecutor;
import com.wush.kafka.consumer.retriever.IKafkaMessageRetriever;
import com.wush.kafka.producer.ProducerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component("com.wush.consumer.kafka.KafkaReplayLimitConsumerExecutor")
public class KafkaReplayLimitConsumerExecutor implements IKafkaConsumerExecutor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaReplayLimitConsumerExecutor.class);

    private  ApplicationContext applicationContext;

    @Autowired
    private  IKafkaMessageRetriever kafkaMessageRetriever;

    @Autowired
    private  ProducerConnector producerConnector;

    @Autowired
    private  ObjectMapper objectMapper;

    @Value("${com.adobe.enterprisecommerce.retry.consumers.hoolihan.max.allowed.replay:5}")
    private final Integer MAX_ALLOWED_REPLAY = 5;

    @Override
    public void execute(ConsumerConfigEntity consumerConfigEntity) {
        /*if (consumerConfigEntity==null){
            logger.error("Cannot execute null consumer configuration");
            return;
        }
        KafkaConsumerResponse retrieveRoqEventsResponse = retrieveRoqEvents(consumerConfigEntity);
        if (retrieveRoqEventsResponse == null || !retrieveRoqEventsResponse.hasEvents()){
            logger.info("No Events for roq {}",consumerConfigEntity.getQualifierName());
            throw new HoolihanNoEventsException();
        }
        IHoolihanQueueMessageConsumer messageConsumer = getConsumerBean(consumerConfigEntity.getExecutorBeanName());
        if (messageConsumer==null) {
            logger.error("Invalid Consumer Bean for topic {}",consumerConfigEntity.getTopicName());
            return;
        }
        Set<Long> messagesAckList = messageConsumer.consume(retrieveRoqEventsResponse.getEvents());
        if (messagesAckList==null) { return; }
        List<ConsumableEvent> unsuccessfulEvents = retrieveRoqEventsResponse.getEvents().stream().
                filter(item -> !messagesAckList.contains(item.getOffset())).
                collect(Collectors.toList());
        unsuccessfulEvents.forEach(event -> {
            Boolean replayStatus;
            try {
                replayStatus = publishEvent(event,consumerConfigEntity);
            }
            catch (IOException exception) {
                logger.error("Error while parsing event {}",event);
                replayStatus = true;
            }
            if (replayStatus) {
                messagesAckList.add(event.getOffset());
            }
        });

        if (!CollectionUtils.isEmpty(messagesAckList)) {
            hoolihanMessagePuller.acknowledgeMessages(consumerConfigEntity.getTopicName(),
                    consumerConfigEntity.getQualifierName(), retrieveRoqEventsResponse.getReceipt(), messagesAckList);
        }*/
    }

    /*private boolean publishEvent(ConsumableEvent event, ConsumerConfigEntity consumerConfigEntity) throws IOException{
        Boolean replayStatus;
        Integer currentReplayCount = findCurrentReplayCount(event);
        Integer allowedReplayLimit = getAllowedReplay(consumerConfigEntity.getReplayLimit());
        if (currentReplayCount < allowedReplayLimit) {
            replayStatus = publishToSourceTopic(getRequestedData(event),consumerConfigEntity.getTopicName(),currentReplayCount);
        } else {
            replayStatus = publishToDLQTopic(getRequestedData(event),consumerConfigEntity.getDeadLetterTopicName());
        }
        return replayStatus;
    }

    private boolean publishToDLQTopic(String requestedData,String dlqName){
        if(StringUtils.isBlank(dlqName))
            return true;
        return hoolihanMessagePublisher.publish(dlqName, requestedData);
    }*/

   /* private boolean publishToSourceTopic(String requestedData,String sourceTopic, Integer currentReplayCount){
        return limitReplayHoolihanPublisher.publish(requestedData, sourceTopic, currentReplayCount);
    }

    private String getRequestedData(ConsumableEvent event) throws IOException{
        Object message = objectMapper.readValue(event.getValue().getContent(), Object.class);
        return objectMapper.writeValueAsString(message);
    }

    private Integer findCurrentReplayCount(ConsumableEvent event) {
        Integer currentReplayCount = 0;
        Map<String, Collection<String>> headers = event.getValue().getHeaders();

        if (headers.get(LIMIT_REPLAY_HEADER) != null) {
            List<String> retryHeader = (ArrayList) event.getValue().getHeaders().get(LIMIT_REPLAY_HEADER);
            currentReplayCount = ReplayCountFinder.findCurrentReplayCount(retryHeader.get(0));
        }
        return ++currentReplayCount;
    }
*/
   /* private Integer getAllowedReplay(Integer replayLimit){
        return replayLimit > MAX_ALLOWED_REPLAY ? MAX_ALLOWED_REPLAY : replayLimit;
    }

    private KafkaConsumerResponse retrieveRoqEvents(ConsumerConfigEntity consumerConfigEntity){
        try {
            return kafkaMessageRetriever.pullMessages(
                    consumerConfigEntity.getTopicName(), consumerConfigEntity.getCountOfMessagesPolled(),
                    consumerConfigEntity.getGroupId());
        }
        catch (Exception exception){
            logger.error("Error while retrieving events: {}", exception);
            return null;
        }
    }

    private IKafkaQueueMessageConsumer getConsumerBean(String executorBeanName){
        return applicationContext.getBean(executorBeanName, IKafkaQueueMessageConsumer.class);
    }*/

}
