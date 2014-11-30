package com.controllers;

import com.Status;
import com.beans.Car;
import com.repos.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Created by George.Mao on 11/14/2014.
 */

@RestController
@RequestMapping("/inv")
public class InventoryControllerRS {

    @Autowired
    private CarRepository carRepo;

    @Autowired
    Environment env ;

    @RequestMapping("/getAllCars")
    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<Car>();
        if(isMongoEnabled())
            cars = carRepo.findAll();
        else
            cars = generateFakeCars();

        cars.forEach(e -> System.out.println(e));

        return cars;
    }

    @RequestMapping("/sellCar")
    public Car sellCar(@RequestParam String vin){
        // protect against invalid/nonexisting vins
        Optional<Car> optCar = Optional.ofNullable(carRepo.findByVin(vin));

        // Unneeded, but functional style of checking the item
        optCar.ifPresent(e -> System.out.println(e) );

        if(optCar.isPresent()) {
            Car c= optCar.get();
            c.setStatus(Status.SOLD);
            c = carRepo.save(c);
            return c;
        }
        return null;
    }

    private boolean isMongoEnabled(){
        List profiles = Arrays.asList(env.getActiveProfiles());
        if(profiles.contains("mongodb"))
            return true;
        return false;
    }

    private ArrayList<Car> generateFakeCars(){
        Car a = new Car("1YSZ32432432", "Tesla", "MS", "Red", Status.SOLD);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(a);

        return cars;

    }
}
