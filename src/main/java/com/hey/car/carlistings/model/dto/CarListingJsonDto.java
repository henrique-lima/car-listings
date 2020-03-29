package com.hey.car.carlistings.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Year;

public class CarListingJsonDto {

    private Long dealerId;
    private String code;
    private String make;
    private String model;
    @JsonProperty("kW")
    private Long kw;
    private Year year;
    private String color;
    private BigDecimal price;

    public CarListingJsonDto() {}

    @JsonCreator
    public CarListingJsonDto(Long dealerId, @JsonProperty(required = true) String code, String make, String model, Long kw, Year year, String color, BigDecimal price) {
        this.dealerId = dealerId;
        this.code = code;
        this.make = make;
        this.model = model;
        this.kw = kw;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public CarListingJsonDto(String code, String make, String model, Long kw, Year year, String color, BigDecimal price) {
        this.code = code;
        this.make = make;
        this.model = model;
        this.kw = kw;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
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

    public Long getKw() {
        return kw;
    }

    public void setKw(Long kw) {
        this.kw = kw;
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
