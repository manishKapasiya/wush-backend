package com.wush.demand.dto.mapper;

import com.wush.demand.db.model.WashDemand;
import com.wush.demand.dto.response.WashDemandResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WashDemandToWashDemandResponseMapper implements
        BeanMapper<WashDemand, WashDemandResponse> {
    @Override
    public WashDemandResponse map(WashDemand source) {
        WashDemandResponse washDemandResponse = new WashDemandResponse();
        washDemandResponse.setCarExternalId(source.getCarExternalId());
        washDemandResponse.setPlace(source.getPlace());
        washDemandResponse.setScheduledAt(source.getScheduledAt());
        washDemandResponse.setServiceType(source.getServiceType());
        washDemandResponse.setUserExternalId(source.getUserExternalId());
        washDemandResponse.setWashDemandRequestId(String.valueOf(source.getWashDemandRequestId()));
        washDemandResponse.setDemandStatus(source.getDemandStatus());
        washDemandResponse.setStatusReason(source.getStatusReason());
        return washDemandResponse;
    }

    @Override
    public WashDemandResponse map(WashDemand source, Map<String, String> headers) {
        return null;
    }
}
