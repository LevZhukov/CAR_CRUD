package com.levzhukov.car;

<<<<<<< HEAD
=======
import org.springframework.stereotype.Component;

@Component
>>>>>>> car
public enum Manufacturer {
    AUDI("Germany"),
    BMW("Germany"),
    CITROEN("France"),
    FIAT("Italy");
<<<<<<< HEAD

    private String country;

    Manufacturer(String country) {
=======
    private String country;
    Manufacturer(String country){
>>>>>>> car
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
