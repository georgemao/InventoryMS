package com.controllers;

import com.services.CarService;
import com.Status;
import com.beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Created by George.Mao on 11/14/2014.
 * This class is sample Car Inventory. It is intended to be a MicroService, providing common REST based services to:
 *  1. View the inventory
 *  2. Modify the inventory
 * The service uses Spring, Spring Boot, Spring Data to interact with a MongoDB.
 * It should be consumed by a web front end or business application to provide end user functionality
 */

@RestController
@RequestMapping("/inv")
public class InventoryControllerRS {

    @Autowired
    CarService carService;

    @Autowired
    Environment env ;

    @RequestMapping("/getAllCars")
    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<Car>();

        cars = carService.findAll();

        cars.forEach(e -> System.out.println(e));

        return cars;
    }

    @RequestMapping("/sellCar")
    public Car sellCar(@RequestParam String vin){
        // protect against invalid/nonexisting vins
        Optional<Car> optCar = Optional.ofNullable(carService.findByVin(vin));

        // Unneeded, but functional style of checking the item
        optCar.ifPresent(e -> System.out.println(e) );

        if(optCar.isPresent()) {
            Car c= optCar.get();
            c.setStatus(Status.SOLD);
            c = carService.save(c);
            return c;
        }
        return null;
    }

    @RequestMapping("/test/{vin}")
    public String test(@PathVariable String vin){
        return vin;
    }

    /**
     * Helper method that determins if mongo is enabled
     * @return
     */
    private boolean isMongoEnabled(){
        List profiles = Arrays.asList(env.getActiveProfiles());
        if(profiles.contains("mongodb"))
            return true;
        return false;
    }
}
