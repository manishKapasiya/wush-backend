package com.wush.demand.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wush.demand.constants.Constants;
import com.wush.demand.db.model.WashDemand;
import com.wush.demand.db.repository.DemandRepository;
import com.wush.demand.dto.mapper.WashDemandRequestToWashDemandMapper;
import com.wush.demand.dto.mapper.WashDemandToWashDemandResponseMapper;
import com.wush.demand.dto.request.WashDemandRequest;
import com.wush.demand.dto.response.WashDemandResponse;
import com.wush.kafka.producer.IProducerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class DemandService implements IDemandService {

    Logger logger = LoggerFactory.getLogger(DemandService.class);

    @Autowired
    private WashDemandRequestToWashDemandMapper washDemandRequestToWashDemandMapper;

    @Autowired
    private WashDemandToWashDemandResponseMapper washDemandToWashDemandResponseMapper;

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private IProducerConnector producerConnector;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("{com.wush.kafka.consumer.producer.demand.topic}")
    private String demandTopic;

    @Override
    public WashDemandResponse requestWash(WashDemandRequest washDemandRequest) {
        WashDemand washDemand = washDemandRequestToWashDemandMapper.map(washDemandRequest);
        washDemand.setDemandStatus(Constants.DEMAND_PROCESSING);
        washDemand.setStatusReason("Sent to processing");
        try{
            washDemand = demandRepository.save(washDemand);
        }catch (Exception exception){
            //throw new WashDemandCreateException("","");
        }
        try{
            // publish this demand request to demand queue
            producerConnector.publish(demandTopic,objectMapper.writeValueAsString(washDemand),"1");
        }catch (Exception e){
            logger.error("Unknown error while publish message : ", washDemand);
        }
        return washDemandToWashDemandResponseMapper.map(washDemand);
    }

}
