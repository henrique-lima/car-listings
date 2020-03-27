package com.hey.car.carlistings.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CarListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dealerId;
    private String code;

    private String make;
    private String model;
    private Long kw;
    private Long year;
    private String color;
    private BigDecimal price;

    public CarListing() {}

    public CarListing(Long dealerId, String code, String make, String model, Long kw, Long year, String color, BigDecimal price) {
        this.dealerId = dealerId;
        this.code = code;
        this.make = make;
        this.model = model;
        this.kw = kw;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public CarListing(String code, String make, String model, Long kw, Long year, String color, BigDecimal price) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
