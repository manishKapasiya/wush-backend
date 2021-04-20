package com.wush.kafka.producer;

import com.wush.kafka.config.KafkaConfigEntity;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class ProducerConnector implements IProducerConnector{

    private static Logger logger = LoggerFactory.getLogger(ProducerConnector.class);

    @Autowired
    private KafkaConfigEntity kafkaConfigEntity;

    public void publish(String topic, String message, String acks){

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigEntity.getBootstrapServer());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG,acks);

        // create the producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        // create the producer record
        ProducerRecord<String,String> record = new ProducerRecord<>(topic,message);

        // send data
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e==null){
                    // message successfully sent
                    logger.info("topic : "+ topic+", partition : "+recordMetadata.partition()
                            +", offset : "+recordMetadata.offset()+", message : "+message);
                }else {
                    // exception occured
                    logger.error("Error while publishing message on to topic : "+ topic
                                    + ", message : "+ message
                            + ", error : "+ e.getMessage());
                }
            }
        });
        producer.close();
    }

}


