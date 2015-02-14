package com.services;

import com.beans.Car;
import com.repos.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implements the CarService interface - loaded by Congfiguration when the 'connectd' profile is enabled
 * Interacts with the CarRepository
 */
public class CarServiceConnected implements CarService{

    @Autowired
    private CarRepository carRepo;

    public CarServiceConnected(){
        System.out.println("CarServiceConnected!!!!!!");
    }

    public List<Car> findAll(){
        return carRepo.findAll();
    }

    @Override
    public Car save(Car c) {
        return carRepo.save(c);
    }

    @Override
    public Car findByVin(String vin) {
        return carRepo.findByVin(vin);
    }


}
