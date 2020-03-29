package com.hey.car.carlistings.rest.controller;

import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.service.CarListingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Year;
import java.util.List;

@RestController
public class CarListingsController {

    private final CarListingsService carListingsService;

    public CarListingsController(CarListingsService carListingsService) {
        this.carListingsService = carListingsService;
    }

    @PostMapping(value = "/upload_csv/{dealerId}", consumes = "text/csv")
    public ResponseEntity saveListingsFromCsv(@PathVariable Long dealerId, @RequestBody List<CarListingCsvDto> carListings) {
        carListingsService.saveCarListings(dealerId, carListings);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/vehicle_listings", consumes = "application/json")
    public ResponseEntity saveListingsFromJson(@Valid @RequestBody List<CarListingJsonDto> carListings) {
        carListingsService.saveCarListings(carListings);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/search", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CarListingJsonDto>> searchListings(@RequestParam(required = false) String make, @RequestParam(required = false) String model,
                                                  @RequestParam(required = false) Year year, @RequestParam(required = false) String color) {
        return ResponseEntity.status(HttpStatus.OK).body(carListingsService.searchListings(make, model, year, color));
    }
}
