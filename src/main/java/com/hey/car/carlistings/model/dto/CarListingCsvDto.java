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

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
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
