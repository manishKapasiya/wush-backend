package com.wush.cars.resource;

import com.wush.cars.dto.request.CarAddRequest;
import com.wush.cars.dto.response.CarDetails;
import com.wush.cars.dto.response.CarDetailsCount;
import com.wush.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cars")
public class CarResource {

    @Autowired
    private CarService carService;

    @PostMapping
    public CarDetails saveCar(@RequestBody CarAddRequest carAddRequest){
        return carService.saveCar(carAddRequest);
    }

    @GetMapping
    public CarDetailsCount getCarsForUser(@RequestParam String userExternalId){
        return carService.getCarsByUserId(userExternalId);
    }

    @GetMapping("/{carId}")
    public CarDetails getCarById(@PathVariable Long carId){
        return carService.getCarById(carId);
    }

}
