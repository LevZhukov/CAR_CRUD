package com.levzhukov.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("{carId}")
    public Car getCarById(@PathVariable("carId") int carId) {
        try {
            return carService.getCarById(carId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car with id " + carId + " was not found", e);
        }
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PatchMapping("{carId}")
    public Car updateCar(@PathVariable("carId") int carId,
                            @RequestBody Car car) {
        try {
            carService.updateCar(carId, car);
            return carService.getCarById(carId);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car with id " + carId + " was not found", e);
        }
    }

    @DeleteMapping("{carId}")
    public Car deleteCar(@PathVariable("carId") int carId) {
        try {
            return carService.deleteCar(carId);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car with id " + carId + " was not found", e);
        }
    }
}
