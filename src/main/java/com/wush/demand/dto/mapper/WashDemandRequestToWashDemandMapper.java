package com.wush.demand.dto.mapper;

import com.wush.demand.db.model.WashDemand;
import com.wush.demand.dto.request.WashDemandRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WashDemandRequestToWashDemandMapper implements BeanMapper<WashDemandRequest, WashDemand> {
    @Override
    public WashDemand map(WashDemandRequest source) {
        WashDemand washDemand = new WashDemand();
        washDemand.setCarExternalId(source.getCarExternalId());
        washDemand.setPlace(source.getPlace());
        washDemand.setScheduledAt(source.getScheduledAt());
        washDemand.setServiceType(source.getServiceType().name());
        washDemand.setUserExternalId(source.getUserExternalId());
        return washDemand;
    }

    @Override
    public WashDemand map(WashDemandRequest source, Map<String, String> headers) {
        return null;
    }
}
