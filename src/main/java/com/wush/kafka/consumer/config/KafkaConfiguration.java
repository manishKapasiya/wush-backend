package com.wush.kafka.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("com.wush.consumers.kafka.KafkaConfiguration")
@ConfigurationProperties("com.wush.consumers.kafka")
public class KafkaConfiguration {
    private String bootstrapServer;

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }
}
