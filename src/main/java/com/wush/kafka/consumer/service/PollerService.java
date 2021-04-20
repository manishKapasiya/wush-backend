package com.wush.kafka.consumer.service;

import com.wush.kafka.consumer.constants.ExecutorVersion;
import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import com.wush.kafka.consumer.exception.KafkaNoEventsException;
import com.wush.kafka.consumer.executor.IKafkaConsumerExecutor;
import com.wush.kafka.consumer.factory.KafkaConsumerExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component("com.adobe.enterprisecommerce.consumers.hoolihan.PollerService")
public class PollerService implements IPollerService {

    @Autowired
    private KafkaConsumerExecutorFactory kafkaConsumerExecutorFactory;

    @Override
    public void poll(ConsumerConfigEntity consumerConfigEntity) {
        if(consumerConfigEntity.isBackOffEnabled()) {
            executeWithBackOff(consumerConfigEntity);
        }
        else {
            execute(consumerConfigEntity);
        }
    }

    private void executeWithBackOff(ConsumerConfigEntity consumerConfigEntity){
        long timeSinceLastExecution = Duration.between(consumerConfigEntity.getLastExecutionTime(), Instant.now())
                .toSeconds();
        long initialDelay = getInitialDelay(consumerConfigEntity.getBackOffInitialDelayCount(),
                consumerConfigEntity.getPollingFrequency());

        if(timeSinceLastExecution < initialDelay){

            if(isEventFound(consumerConfigEntity)){
                consumerConfigEntity.setLastExecutionTime(Instant.now());
            }

        } else if ( timeSinceLastExecution >= consumerConfigEntity.getCurrentPollingFrequency()){

            if(isEventFound(consumerConfigEntity)){
                // reset last execution time and current polling frequency
                consumerConfigEntity.setLastExecutionTime(Instant.now());
                consumerConfigEntity.resetCurrentPollingFrequency();
            }
            else {
                // increase polling frequency
                backOffCurrentPolingFrequency(consumerConfigEntity);
            }
        }
    }

    private void execute(ConsumerConfigEntity consumerConfigEntity){
        ExecutorVersion version = consumerConfigEntity.getExecutorVersion();
        IKafkaConsumerExecutor executor = kafkaConsumerExecutorFactory.getExecutor(version);
        executor.execute(consumerConfigEntity);
    }

    private boolean isEventFound(ConsumerConfigEntity consumerConfigEntity){
        try{
            execute(consumerConfigEntity);
        } catch (KafkaNoEventsException noEventsFoundException){
            return false;
        }
        return true;
    }

    private long getInitialDelay(int backOffInitialDelayCount, int pollingFrequency){
        long initialDelay = backOffInitialDelayCount * pollingFrequency;

        initialDelay = pollingFrequency>initialDelay ? pollingFrequency + 10 : initialDelay;

        return initialDelay;
    }

    private void backOffCurrentPolingFrequency(ConsumerConfigEntity consumerConfigEntity){
        int backOffTime = consumerConfigEntity.getCurrentPollingFrequency()*
                consumerConfigEntity.getBackOffMultiplier();

        if (backOffTime > consumerConfigEntity.getBackOffLimit()){
            consumerConfigEntity.setCurrentPollingFrequency(consumerConfigEntity.getBackOffLimit());
        } else {
            consumerConfigEntity.setCurrentPollingFrequency(backOffTime);
        }
    }

}
