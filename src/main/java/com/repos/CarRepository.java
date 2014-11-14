package com.repos;

import java.util.List;

import com.Status;
import com.beans.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String>{
	public List<Car> findByMake(String make);
    public List<Car> findByColor(String color);

    public Car findByVin(String vin);

    public List<Car> findByStatus(Status status);
}

