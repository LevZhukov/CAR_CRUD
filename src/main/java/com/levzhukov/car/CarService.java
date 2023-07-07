package com.levzhukov.car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public int addCar(Car car) {
        carRepository.save(car);
        return car.getId();
    }

    public Car getCarById(int carId) throws IllegalArgumentException {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            return carOptional.get();
        } else throw new IllegalArgumentException();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(int carId) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (!optionalCar.isPresent()){
            throw new Exception();
        }
        else carRepository.deleteById(carId);
    }
}

