package com.levzhukov;

import com.levzhukov.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositoryTest extends JpaRepository<Car, Integer> {
}