package com.hey.car.carlistings.model.dto;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class CarListingCsvDto {

    @CsvBindByName(required = true)
    private String code;
    @CsvBindByName(column = "make/model")
    private String makeModel;
    @CsvBindByName(column = "power-in-ps")
    private Long powerPs;
    @CsvBindByName(column = "year")
    private Integer year;
    @CsvBindByName(column = "color")
    private String color;
    @CsvBindByName(column = "price")
    private BigDecimal price;

    public CarListingCsvDto() {}

    public CarListingCsvDto(String code, String makeModel, Long powerPs, Integer year, String color, BigDecimal price) {
        this.code = code;
        this.makeModel = makeModel;
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

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public Long getPowerPs() {
        return powerPs;
    }

    public void setPowerPs(Long powerPs) {
        this.powerPs = powerPs;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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
