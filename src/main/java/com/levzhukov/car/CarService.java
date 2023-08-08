package com.levzhukov.car;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);

    }

    public Car getCarById(int carId) throws IllegalArgumentException {

       return carRepository.findById(carId).orElseThrow(IllegalArgumentException::new);

    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car deleteCar(int carId) throws IllegalArgumentException {

        Car removedCar = getCarById(carId);
        carRepository.deleteById(carId);
        return removedCar;
    }

    @Transactional
    public void updateCar(int carId, Car car) throws IllegalArgumentException {

            Car currentCar = getCarById(carId);
            if(car.getModel() != null) currentCar.setModel(car.getModel());
            if(car.getIssueDate() != null) currentCar.setIssueDate(car.getIssueDate());
            if(car.getCost() != 0) currentCar.setCost(car.getCost());
    }
}

