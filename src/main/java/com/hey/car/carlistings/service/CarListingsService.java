package com.hey.car.carlistings.service;

import com.hey.car.carlistings.model.CarListing;
import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.repository.CarListingRepository;
import com.hey.car.carlistings.util.CarListingUtil;
import org.springframework.stereotype.Service;

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

    private Long saveOrUpdateListing(CarListing carListing) {
        carListingRepository.findByDealerIdAndCode(carListing.getDealerId(), carListing.getCode()).stream()
                .findAny().ifPresent(cl -> carListing.setId(cl.getId()));
        return carListingRepository.save(carListing).getId();
    }

    public List<CarListingJsonDto> searchListings(String make, String model, Long year, String color) {
        return carListingRepository.findAll().stream()
                .map(cl -> new CarListingJsonDto(cl.getDealerId(), cl.getCode(),
                        cl.getMake(), cl.getModel(), cl.getKw(),
                        cl.getYear(), cl.getColor(), cl.getPrice()))
                .collect(Collectors.toList());
    }
}
