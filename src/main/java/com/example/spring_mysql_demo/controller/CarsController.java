package com.example.spring_mysql_demo.controller;

import com.example.spring_mysql_demo.models.Car;
import com.example.spring_mysql_demo.models.User;
import com.example.spring_mysql_demo.repositories.CarRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class CarsController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/{id}")
public Car read(@PathVariable int id){
        var optinalCar = carRepository.findById(id);
        return optinalCar.orElse(null);
    }

    @PostMapping("/")
    public Car create(@RequestBody Car car){
        carRepository.save(car);
        return car;
    }
}