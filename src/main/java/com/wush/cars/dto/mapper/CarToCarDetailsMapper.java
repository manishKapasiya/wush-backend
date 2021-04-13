package com.wush.cars.dto.mapper;

import com.wush.cars.db.model.Car;
import com.wush.cars.dto.response.CarDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CarToCarDetailsMapper implements BeanMapper<Car, CarDetails> {
    @Override
    public CarDetails map(Car source) {
        CarDetails carDetails = new CarDetails();
        carDetails.setBrand(source.getBrand());
        carDetails.setCarId(source.getCarId());
        carDetails.setCarType(source.getCarType().name());
        carDetails.setName(source.getName());
        carDetails.setUserId(source.getUserExternalId());
        return carDetails;
    }

    @Override
    public CarDetails map(Car source, Map<String, String> headers) {
        return null;
    }
}
