package com.hey.car.carlistings.service;

import com.hey.car.carlistings.model.CarListing;
import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.repository.CarListingRepository;
import com.hey.car.carlistings.util.CarListingUtil;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarListingsService {

    private final CarListingRepository carListingRepository;
    private final CarListingUtil carListingUtil;

    public CarListingsService(CarListingRepository carListingRepository, CarListingUtil carListingUtil) {
        this.carListingRepository = carListingRepository;
        this.carListingUtil = carListingUtil;
    }

    public void saveCarListings(Long dealerId, List<CarListingCsvDto> carListings) {
        carListings.stream().forEach(cl -> saveOrUpdateListing(
                new CarListing(dealerId, cl.getCode(), cl.getMake(), cl.getModel(),
                        carListingUtil.convertPsToKw(cl.getPowerPs()), cl.getYear(),
                        cl.getColor(), cl.getPrice())
        ));
    }

    public void saveCarListings(List<CarListingJsonDto> carListings) {
        carListings.stream().forEach(cl -> saveOrUpdateListing(
                new CarListing(cl.getCode(), cl.getMake(), cl.getModel(), cl.getKw(),
                        cl.getYear(), cl.getColor(), cl.getPrice())
        ));
    }

    protected Long saveOrUpdateListing(CarListing carListing) {
        carListingRepository.findByDealerIdAndCode(carListing.getDealerId(), carListing.getCode()).stream()
                .findFirst().ifPresent(cl -> carListing.setId(cl.getId()));
        return carListingRepository.save(carListing).getId();
    }

    public List<CarListingJsonDto> searchListings(String make, String model, Year year, String color) {
        return carListingRepository.findByMakeOrModelOrYearOrColor(make, model, year, color).stream()
                .map(cl -> new CarListingJsonDto(cl.getDealerId(), cl.getCode(),
                        cl.getMake(), cl.getModel(), cl.getKw(),
                        cl.getYear(), cl.getColor(), cl.getPrice()))
                .collect(Collectors.toList());
    }

    public List<CarListingJsonDto> searchListings() {
        return this.searchListings(null, null, null, null);
    }
}
