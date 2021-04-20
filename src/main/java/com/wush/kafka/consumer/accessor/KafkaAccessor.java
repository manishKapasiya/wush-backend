package com.wush.kafka.consumer.accessor;

import com.wush.kafka.consumer.config.KafkaConfiguration;
import com.wush.kafka.consumer.entity.KafkaConsumerResponse;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

@Component("com.wush.consumers.kafka.KafkaAccessor")
public class KafkaAccessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAccessor.class);

    @Autowired
    private KafkaConfiguration kafkaConfiguration;


    public KafkaConsumerResponse pullMessages(String topicName,
                                              int numberOfMessages,
                                              String groupId
                                              ) {
        KafkaConsumerResponse kafkaConsumerResponse = new KafkaConsumerResponse();

        // create consumer config
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServer());
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // create consumer
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topicName));

        int i = 0;
        // poll for new data
        while(i < numberOfMessages){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String,String> record : records){
                logger.info("Key : "+ record.key()+" , Value : "+record.value());
                logger.info("Partition: "+record.partition()+", Offset : "+record.offset());
            }

            kafkaConsumerResponse.getConsumerRecords().add(records);
            i++;
        }
        return kafkaConsumerResponse;
    }

    public void acknowledgeMessages(String topicName,Set<Long> offsetIds) {
    }
}
