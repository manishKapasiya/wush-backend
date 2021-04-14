package com.wush.demand.dto.request;

import com.wush.demand.constants.ServiceType;

import java.sql.Timestamp;

public class WashDemandRequest {

    private String userExternalId;

    private String carExternalId;

    private Timestamp scheduledAt;

    private String place;

    private ServiceType serviceType;

    private String demandStatus;

    private String statusReason;

    public String getUserExternalId() {
        return userExternalId;
    }

    public void setUserExternalId(String userExternalId) {
        this.userExternalId = userExternalId;
    }

    public String getCarExternalId() {
        return carExternalId;
    }

    public void setCarExternalId(String carExternalId) {
        this.carExternalId = carExternalId;
    }

    public Timestamp getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Timestamp scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(String demandStatus) {
        this.demandStatus = demandStatus;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
}
