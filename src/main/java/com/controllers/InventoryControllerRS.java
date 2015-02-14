package com.controllers;

import com.services.CarService;
import com.Status;
import com.beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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

    /**
     * This provides listing of all cars in the inventory
     * @return
     */
    @RequestMapping("/getAllCars")
    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<Car>();

        cars = carService.findAll();

        cars.forEach(e -> System.out.println(e));

        return cars;
    }

    /**
     * This method sells a Car, using a request param in the URL
     * @param vin the URL param
     * @return the car that was sold
     */
    @RequestMapping("/sellCar")
    public @ResponseBody Car sellCar(@RequestParam String vin){
        return sellOneCar(vin);
    }

    /**
     * This method sells a Car, using a path param in the URL
     * @param vin the PATH param
     * @return the car that was sold
     */
    @RequestMapping("/sellCar/{vin}")
    public @ResponseBody Car sellCarPathParam(@PathVariable String vin){
        return sellOneCar(vin);
    }

    /**
     * Utility Helper method that sells a car
     * @param vin
     * @return
     */
    private Car sellOneCar(String vin){
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
