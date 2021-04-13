package com.wush.cars.utils;

import com.wush.cars.constants.CarType;

public class CarTypeFinder {

    public static CarType findCarType(String carBrand, String carName){
        StringBuilder car = new StringBuilder();
        car.append(carBrand);
        car.append(carName);

        switch (car.toString()){
            case "HyundaiCreta":
                return CarType.COMPACT_SUV;
            case "HyundaiSantro":
            case "HyundaiI10":
            case "HyundaiI20":
            case "MarutiAlto":
            case "MarutiSwift":
            case "MarutiBaleno":
                return CarType.HATCHBACK;
            case "MarutiCiaz":
                return CarType.SEDAN;
            case "HondaAmaze":
                return CarType.COMPACT_SEDAN;
            case "HondaCRV":
                return CarType.SUV;
            default:
                return null;
        }
    }
}
