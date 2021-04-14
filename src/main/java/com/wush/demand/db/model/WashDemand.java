package com.wush.demand.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="wash_demand")
public class WashDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long washDemandRequestId;

    @Column(name="user_external_id")
    private String userExternalId;

    @Column(name="car_external_id")
    private String carExternalId;

    @Column(name="scheduled_at")
    private Timestamp scheduledAt;

    @Column(name="place")
    private String place;

    @Column(name="service_type")
    private String serviceType;

    @Column(name = "demand_status")
    private String demandStatus;

    @Column(name = "status_reason")
    private String statusReason;

    public Long getWashDemandRequestId() {
        return washDemandRequestId;
    }

    public void setWashDemandRequestId(Long washDemandRequestId) {
        this.washDemandRequestId = washDemandRequestId;
    }

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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
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
