package com.wush.demand.resource;

import com.wush.demand.dto.request.WashDemandRequest;
import com.wush.demand.dto.response.WashDemandResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demand")
public class DemandResource {

    @PostMapping
    public WashDemandResponse demandWash(@RequestBody WashDemandRequest washDemandRequest){
        return null;
    }
}
