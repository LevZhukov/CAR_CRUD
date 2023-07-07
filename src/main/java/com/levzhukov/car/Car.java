package com.levzhukov.car;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Car {
    @Id
    private int id;
    private String model;
    private LocalDate issueDate;
    private int cost;
    private Manufacturer manufacturer;

}
