package com.levzhukov.car;

import org.springframework.stereotype.Component;

@Component
public enum Manufacturer {
    AUDI("Germany"),
    BMW("Germany"),
    CITROEN("France"),
    FIAT("Italy");
    private String country;
    Manufacturer(String country){
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
