package com.wush.cars.db.repository;

import com.wush.cars.db.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CarRepository extends CrudRepository<Car,Long> {

    @Query("select c from Car c where c.userExternalId=?1")
    List<Car> getCarsByUser(String userExternalId);
}
