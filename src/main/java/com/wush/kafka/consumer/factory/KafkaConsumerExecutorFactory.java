package com.wush.kafka.consumer.factory;

import com.wush.kafka.consumer.constants.ExecutorVersion;
import com.wush.kafka.consumer.executor.IKafkaConsumerExecutor;
import com.wush.kafka.consumer.executor.impl.KafkaReplayLimitConsumerExecutor;
import com.wush.kafka.consumer.executor.impl.KafkaSimpleConsumerExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com.adobe.enterprisecommerce.consumer.hoolihan.HoolihanConsumerExecutorFactory")
public class KafkaConsumerExecutorFactory {

    @Autowired
    KafkaReplayLimitConsumerExecutor hoolihanReplayLimitConsumerExecutor;

    @Autowired
    KafkaSimpleConsumerExecutor hoolihanSimpleConsumerExecutor;


    public IKafkaConsumerExecutor getExecutor(ExecutorVersion executorVersion){
        if (executorVersion==null){ return hoolihanSimpleConsumerExecutor;}
        switch (executorVersion){
            case REPLAY_LIMIT:
                return hoolihanReplayLimitConsumerExecutor;
            case SIMPLE:
            default:
                return hoolihanSimpleConsumerExecutor;
        }
    }
}
