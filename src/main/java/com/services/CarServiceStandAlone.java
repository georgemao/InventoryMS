package com.services;

import com.Status;
import com.beans.Car;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CarServiceStandAlone implements CarService {
    public CarServiceStandAlone(){
        System.out.println("CarServiceStandAlone!!!!!!!!!!!");
    }


    @Override
    public List<Car> findAll() {
        return generateFakeCars();
    }

    @Override
    public Car save(Car c) {
        return null;
    }

    @Override
    public Car findByVin(String vin) {
        return null;
    }

    private ArrayList<Car> generateFakeCars(){
        Car a = new Car("1YSZ32432432", "Tesla", "MS", "Red", Status.SOLD);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(a);

        return cars;

    }
}
