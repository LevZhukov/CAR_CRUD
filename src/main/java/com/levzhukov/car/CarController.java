package com.levzhukov.car;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @GetMapping
    public String getAllCars() {
        return "All cars should be there";
    }
    @GetMapping("{carId}")
    public String getCarById(@PathVariable("carId") String carId){
        return "Car with id " + carId + " will be shown here";
    }
    @PostMapping
    public String recordCar(@RequestBody Car car){
        return "new car has been recorded";
    }
    @PatchMapping("{carId}")
    public String updateCar(@PathVariable("carId") int carId,
                            @RequestParam(required = false) String model,
                            @RequestParam(required = false) LocalDate issueDate,
                            @RequestParam(required = false) int cost,
                            @RequestParam(required = false) Manufacturer manufacturer){
        return "Car record updated";
    }
}
