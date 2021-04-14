package com.wush.demand.service;

import com.wush.demand.constants.Constants;
import com.wush.demand.db.model.WashDemand;
import com.wush.demand.db.repository.DemandRepository;
import com.wush.demand.dto.mapper.WashDemandRequestToWashDemandMapper;
import com.wush.demand.dto.mapper.WashDemandToWashDemandResponseMapper;
import com.wush.demand.dto.request.WashDemandRequest;
import com.wush.demand.dto.response.WashDemandResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class DemandService implements IDemandService {

    @Autowired
    private WashDemandRequestToWashDemandMapper washDemandRequestToWashDemandMapper;

    @Autowired
    private WashDemandToWashDemandResponseMapper washDemandToWashDemandResponseMapper;

    @Autowired
    private DemandRepository demandRepository;

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
        }catch (Exception e){

        }
        return washDemandToWashDemandResponseMapper.map(washDemand);
    }

}
