package com.repos;

import java.util.List;

import com.Status;
import com.beans.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data Repository - Extends from MongoRepository
 * Adds domain/project specific queries to auto generated Repo functionality
 */
public interface CarRepository extends MongoRepository<Car, String>{

	public List<Car> findByMake(String make);
    public List<Car> findByColor(String color);
    public Car findByVin(String vin);
    public List<Car> findByStatus(Status status);
}

