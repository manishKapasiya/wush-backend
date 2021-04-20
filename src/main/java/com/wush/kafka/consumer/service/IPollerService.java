package com.wush.kafka.consumer.service;

import com.wush.kafka.consumer.entity.ConsumerConfigEntity;

public interface IPollerService {

    void poll(ConsumerConfigEntity consumerConfigEntity);
}
