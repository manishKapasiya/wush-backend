package com.wush.kafka.consumer.initializer;

import com.wush.kafka.consumer.config.KafkaConsumerAggregator;
import com.wush.kafka.consumer.entity.ConsumerConfigEntity;
import com.wush.kafka.consumer.service.IPollerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@Component("com.adobe.enterprisecommerce.consumer.hoolihan.HoolihanConsumersInitializer")
public class KafkaConsumersInitializer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumersInitializer.class);

    @Autowired
    private  KafkaConsumerAggregator consumerAggregator;

    @Autowired
    private IPollerService pollerService;

    /**
     * Creates a scheduled service executor and schedules the consumer according to the configuration provided in
     * configuration entities
     */
    @PostConstruct
    public void initConsumerThreads() {
        List<ConsumerConfigEntity> entities = getConsumerConfigEntities();
        if (CollectionUtils.isEmpty(entities)) { return; }
        int index = 0;
        for (ConsumerConfigEntity consumerConfigEntity : entities) {
            if (!consumerConfigEntity.getConsumerEnabled()) { continue; }

            int numPollers = consumerConfigEntity.getNumPollersPerTopic();
            ScheduledExecutorService service = Executors.newScheduledThreadPool(numPollers);
            for (int i = 0; i < numPollers; i++) {
                createScheduledExecutorService(service, consumerConfigEntity, index, i);
            }
            index++;
        }
    }

    private void createScheduledExecutorService(
            ScheduledExecutorService service,
            ConsumerConfigEntity consumerConfigEntity,
            int index,
            int offset
    ) {
        service.scheduleWithFixedDelay(() -> {
            try {
                pollerService.poll(consumerConfigEntity);
            }catch (Exception exception){
                logger.error("Unknown Error occurred in consumer : {} , Exception : {}, Stack Trace: {}",
                        consumerConfigEntity, exception.getMessage(),
                        exception.getStackTrace());
            }
        }, (10 * index + offset), consumerConfigEntity.getPollingFrequency(), SECONDS);
    }

    private List<ConsumerConfigEntity> getConsumerConfigEntities(){
        if (consumerAggregator == null || consumerAggregator.getConsumerConfigs()== null)
            return Collections.emptyList();

        List<ConsumerConfigEntity> entities = new ArrayList<>();
        consumerAggregator.getConsumerConfigs().forEach(consumerConfig -> {
            if (consumerConfig!=null && !CollectionUtils.isEmpty(consumerConfig.getConsumerConfigEntities()))
                entities.addAll(consumerConfig.getConsumerConfigEntities());
        });
        return entities;
    }
}