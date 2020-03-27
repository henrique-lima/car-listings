package com.hey.car.carlistings.rest.controller;

import com.hey.car.carlistings.model.CarListing;
import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.rest.converter.CarListings;
import com.hey.car.carlistings.service.CarListingsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CarListingsController {

    private final CarListingsService carListingsService;

    public CarListingsController(CarListingsService carListingsService) {
        this.carListingsService = carListingsService;
    }

    @PostMapping(value = "/upload_csv/{dealerId}", consumes = "text/csv")
    @ResponseStatus(HttpStatus.CREATED)
    public CarListings saveListingsFromCsv(@PathVariable Long dealerId, @RequestBody CarListings carListings) {
        carListingsService.saveCarListings(dealerId, carListings.getList());
        return carListings;
    }

    @PostMapping(value = "/vehicle_listings", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CarListingJsonDto[] saveListingsFromJson(@RequestBody CarListingJsonDto[] carListings) {
        carListingsService.saveCarListings(Arrays.asList(carListings));
        return carListings;
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CarListingJsonDto> searchListings(@RequestParam(required = false) String make, @RequestParam(required = false) String model,
                                                  @RequestParam(required = false) Long year, @RequestParam(required = false) String color) {
        return carListingsService.searchListings(make, model, year, color);
    }
}
