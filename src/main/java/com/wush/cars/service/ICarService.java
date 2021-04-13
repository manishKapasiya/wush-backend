package com.wush.cars.service;

import com.wush.cars.dto.request.CarAddRequest;
import com.wush.cars.dto.response.CarDetails;
import com.wush.cars.dto.response.CarDetailsCount;

public interface ICarService {

    CarDetails saveCar(CarAddRequest carAddRequest);
    CarDetails getCarById(Long carId);
    CarDetailsCount getCarsByUserId(String userExternalId);
}
