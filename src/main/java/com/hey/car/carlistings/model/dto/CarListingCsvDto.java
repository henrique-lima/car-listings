package com.hey.car.carlistings.model.dto;

import java.math.BigDecimal;

public class CarListingCsvDto {

    public CarListingCsvDto(String code, String make, String model, Long powerPs, Long year, String color, BigDecimal price) {
        this.code = code;
        this.make = make;
        this.model = model;
        this.powerPs = powerPs;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    private String code;
    private String make;
    private String model;
    private Long powerPs;
    private Long year;
    private String color;
    private BigDecimal price;
}
