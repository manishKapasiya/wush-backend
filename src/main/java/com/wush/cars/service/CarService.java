package com.wush.cars.service;

import com.wush.cars.db.model.Car;
import com.wush.cars.db.repository.CarRepository;
import com.wush.cars.dto.mapper.CarAddRequestToCarMapper;
import com.wush.cars.dto.mapper.CarToCarDetailsMapper;
import com.wush.cars.dto.request.CarAddRequest;
import com.wush.cars.dto.response.CarDetails;
import com.wush.cars.dto.response.CarDetailsCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wush.cars.utils.CarTypeFinder.findCarType;

@Component
public class CarService implements ICarService {

    @Autowired
    private CarAddRequestToCarMapper carAddRequestToCarMapper;

    @Autowired
    private CarToCarDetailsMapper carToCarDetailsMapper;

    @Autowired
    private CarRepository carRepository;


    @Override
    public CarDetails saveCar(CarAddRequest carAddRequest) {
        Car car = carAddRequestToCarMapper.map(carAddRequest);
        car.setCarType(findCarType(carAddRequest.getBrand(),carAddRequest.getName()));
        car = carRepository.save(car);
        CarDetails carDetails= carToCarDetailsMapper.map(car);
        return carDetails;
    }

    @Override
    public CarDetails getCarById(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            CarDetails carDetails = carToCarDetailsMapper.map(car.get());
            return carDetails;
        }
        return null;
    }

    @Override
    public CarDetailsCount getCarsByUserId(String userExternalId) {
        List<Car> cars= carRepository.getCarsByUser(userExternalId);
        List<CarDetails> carDetails =cars.stream().map(car ->
                carToCarDetailsMapper.map(car)).collect(Collectors.toList());
        CarDetailsCount carDetailsCount = new CarDetailsCount();
        carDetailsCount.setCount(carDetails.size());
        carDetailsCount.setCarDetails(carDetails);
        return carDetailsCount;
    }

}
