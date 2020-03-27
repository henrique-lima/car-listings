package com.hey.car.carlistings.rest.converter;

import com.hey.car.carlistings.model.dto.CarListingCsvDto;

import java.util.List;

public class CarListings {

    private List<CarListingCsvDto> list;

    public CarListings(List<CarListingCsvDto> list) {
        this.list = list;
    }

    public List<CarListingCsvDto> getList() {
        return list;
    }

    public void setList(List<CarListingCsvDto> list) {
        this.list = list;
    }
}
