package com.hey.car.carlistings.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Objects;

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
    private Year year;
    private String color;
    private BigDecimal price;

    public CarListing() {}

    public CarListing(Long dealerId, String code, String make, String model, Long kw, Year year, String color, BigDecimal price) {
        this.dealerId = dealerId;
        this.code = code;
        this.make = make;
        this.model = model;
        this.kw = kw;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public CarListing(String code, String make, String model, Long kw, Year year, String color, BigDecimal price) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarListing that = (CarListing) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dealerId, that.dealerId) &&
                Objects.equals(code, that.code) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(kw, that.kw) &&
                Objects.equals(year, that.year) &&
                Objects.equals(color, that.color) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dealerId, code, make, model, kw, year, color, price);
    }
}
