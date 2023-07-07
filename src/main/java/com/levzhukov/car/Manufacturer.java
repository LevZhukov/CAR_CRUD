package com.levzhukov.car;

public enum Manufacturer {
    AUDI("Germany"),
    BMW("Germany"),
    CITROEN("France"),
    FIAT("Italy");

    private String country;

    Manufacturer(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
