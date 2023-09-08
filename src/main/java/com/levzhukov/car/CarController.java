package com.levzhukov.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars(Pageable pageable) {
        return carService.getAllCars(pageable);
    }

    @GetMapping("{carId}")
    public Car getCarById(@PathVariable("carId") Long carId) {
        try {
            return carService.getCarById(carId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car with id " + carId + " was not found", e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addCar(@RequestBody Car car, UriComponentsBuilder ucb) {

        Car savedCar = carService.addCar(car);
        URI locationOfNewCashCard = ucb
                .path("api/car/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PatchMapping("{carId}")
    public Car updateCar(@PathVariable("carId") Long carId,
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
    public Car deleteCar(@PathVariable("carId") Long carId) {
        try {
            return carService.deleteCar(carId);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "car with id " + carId + " was not found", e);
        }
    }
}
