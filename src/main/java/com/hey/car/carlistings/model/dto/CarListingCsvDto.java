package com.hey.car.carlistings.model.dto;

import java.math.BigDecimal;
import java.time.Year;

public class CarListingCsvDto {

    private String code;
    private String make;
    private String model;
    private Long powerPs;
    private Year year;
    private String color;
    private BigDecimal price;

    public CarListingCsvDto(String code, String make, String model, Long powerPs, Year year, String color, BigDecimal price) {
        this.code = code;
        this.make = make;
        this.model = model;
        this.powerPs = powerPs;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPowerPs() {
        return powerPs;
    }

    public void setPowerPs(Long powerPs) {
        this.powerPs = powerPs;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
