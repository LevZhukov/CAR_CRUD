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
        if (optionalCar.isEmpty()) {
            throw new Exception();
        } else carRepository.deleteById(carId);
    }

    @Transactional
    public void updateCar(int carId, Car car) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car carFromDB = optionalCar.get();
            if(car.getModel() != null) carFromDB.setModel(car.getModel());
            if(car.getIssueDate() != null) carFromDB.setIssueDate(car.getIssueDate());
            if(car.getCost() != 0) carFromDB.setCost(car.getCost());
        } else throw new Exception();
    }
}

