package com.levzhukov.car;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    public Car getCarById(Long carId) throws IllegalArgumentException {

       return carRepository.findById(carId).orElseThrow(IllegalArgumentException::new);

    }

    public List<Car> getAllCars(Pageable pageable) {
        Page<Car> page = carRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "cost"))
                )
        );

        return page.getContent();
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(Pageable pageable) {
        Page<Car> page = carRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }



    public Car deleteCar(Long carId) throws IllegalArgumentException {

        Car removedCar = getCarById(carId);
        carRepository.deleteById(carId);
        return removedCar;
    }

    @Transactional
    public void updateCar(Long carId, Car car) throws IllegalArgumentException {

            Car currentCar = getCarById(carId);
            if(car.getModel() != null) currentCar.setModel(car.getModel());
            if(car.getIssueDate() != null) currentCar.setIssueDate(car.getIssueDate());
            if(car.getCost() != 0) currentCar.setCost(car.getCost());
    }
}

