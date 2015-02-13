package com.services;

import com.beans.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Interface for providing services to manage the car inventory
 */
public interface CarService{

    public List<Car> findAll();
    public Car save(Car c);
    public Car findByVin(String vin);
}
