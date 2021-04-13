package com.wush.cars.dto.mapper;

import com.wush.cars.db.model.Car;
import com.wush.cars.dto.request.CarAddRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CarAddRequestToCarMapper implements BeanMapper<CarAddRequest, Car> {
    @Override
    public Car map(CarAddRequest source) {
        Car car = new Car();
        car.setBrand(source.getBrand());
        car.setName(source.getName());
        car.setUserExternalId(source.getUserExternalId());
        return car;
    }

    @Override
    public Car map(CarAddRequest source, Map<String, String> headers) {
        return null;
    }
}
