package com.wush.demand.service;

import com.wush.demand.dto.request.WashDemandRequest;
import com.wush.demand.dto.response.WashDemandResponse;

public interface IDemandService {

    WashDemandResponse requestWash(WashDemandRequest washDemandRequest);

}
