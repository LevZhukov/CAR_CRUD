package com.levzhukov;

import com.levzhukov.car.CarConfiguration;
import com.levzhukov.car.CarController;
import com.levzhukov.car.CarRepository;
import com.levzhukov.car.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    CarConfiguration carConfiguration;
    @Autowired
    CarController controller;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarService carService;

    @Test
    public void carConfigurationLoads() {
        assertThat(carConfiguration).isNotNull();
    }

    @Test
    public void controllerLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void carRepositoryLoads() {
        assertThat(carRepository).isNotNull();
    }

    @Test
    public void carServiceLoads() {
        assertThat(carService).isNotNull();
    }
}
