package com.wush.supply.service;

import com.wush.demand.dto.request.WashDemandRequest;

public class SupplyDemandService implements ISupplyDemandService {

    @Override
    public void supplyDemand(WashDemandRequest demandRequest) {
        // identify washers with given criteria.
        // send request to all of them in once.
        // finally assign the washer to the request.
        // push update wash demand request status message to assigned/declined.
    }

}
