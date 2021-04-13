package com.wush.cars.dto.response;

import java.util.List;

public class CarDetailsCount {

    private Integer count;

    private List<CarDetails> carDetails;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CarDetails> getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(List<CarDetails> carDetails) {
        this.carDetails = carDetails;
    }
}
