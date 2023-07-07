package com.levzhukov.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cost;
    private LocalDate issueDate;

    private String model;
    private Manufacturer manufacturer;

    public Car() {
    }

    public Car(int cost, String model, LocalDate issueDate) {
        this.cost = cost;
        this.issueDate = issueDate;
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", cost=" + cost +
                ", issueDate=" + issueDate +
                ", model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
