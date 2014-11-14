package com.controllers;

import com.Status;
import com.beans.Car;
import com.repos.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getAllCars")
    public List<Car> getAllCars(){
        List<Car> cars = carRepo.findAll();

        cars.forEach(e -> System.out.println(e));

        return cars;
    }

    @RequestMapping("/soldCar")
    public Car updateCar(@RequestParam String vin){
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


}
