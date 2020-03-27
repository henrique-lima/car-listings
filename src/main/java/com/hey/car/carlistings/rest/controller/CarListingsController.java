package com.hey.car.carlistings.rest.controller;

import com.hey.car.carlistings.model.CarListing;
import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.rest.converter.CarListings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarListingsController {

    @PostMapping(value = "/upload_csv/{dealerId}", consumes = "text/csv")
    @ResponseStatus(HttpStatus.CREATED)
    public CarListings saveListingsFromCsv(@RequestBody CarListings carListings) {
        return carListings;
    }

    @PostMapping(value = "/vehicle_listings", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CarListing[] saveListingsFromJson(@RequestBody CarListing[] carListings) {
        return carListings;
    }
}
