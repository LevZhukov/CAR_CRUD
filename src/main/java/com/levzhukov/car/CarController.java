package com.levzhukov.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public String getCarById(@PathVariable("carId") int carId) {
        try {
            return carService.getCarById(carId).toString();
        } catch (IllegalArgumentException e) {
            return "car with id " + carId + " was not found";
        }
    }

    @PostMapping
    public String addCar(@RequestBody Car car) {
        int id = carService.addCar(car);
        return "new car has been recorded with id " + id;
    }

    @PatchMapping("{carId}")
    public String updateCar(@PathVariable("carId") int carId,
                            @RequestBody Car car)
                             {
        try {
            carService.updateCar(carId, car);
            return "car with Id " + carId + " was updated";
        }
        catch (Exception e){
            return "no car with Id " + carId + " was found";
        }
    }

    @DeleteMapping("{carId}")
    public String deleteCar(@PathVariable("carId") int carId) {
        try {
            carService.deleteCar(carId);
            return "car with Id " + carId + " was deleted";
        } catch (Exception e) {
            return "no car with Id " + carId + " was found";
        }
    }
}
