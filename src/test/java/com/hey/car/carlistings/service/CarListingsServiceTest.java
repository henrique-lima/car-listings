package com.hey.car.carlistings.service;

import com.hey.car.carlistings.model.CarListing;
import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.repository.CarListingRepository;
import com.hey.car.carlistings.util.CarListingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarListingsServiceTest {

    @Mock
    private CarListingUtil carListingUtil;
    @Mock
    private CarListingRepository carListingRepository;

    private CarListingsService carListingsService;
    private CarListingsService carListingsServiceSpy;

    @BeforeEach
    public void init() {
        carListingsService = new CarListingsService(carListingRepository, carListingUtil);
        carListingsServiceSpy = Mockito.spy(carListingsService);
    }

    @Test
    public void shouldSaveCsvList() {
        CarListingCsvDto carListingDto1 = new CarListingCsvDto("c1", "Renault/4L",
                150l, 1983, "white", new BigDecimal(9990));
        CarListingCsvDto carListingDto2 = new CarListingCsvDto("c2", "Fiat/Uno",
                150l, 1990, "black", new BigDecimal(7990));
        List<CarListingCsvDto> carListingCsvDtoList = Arrays.asList(carListingDto1, carListingDto2);

        CarListing carListing1 = new CarListing(1l, "c1", "Renault", "4L",
                110l, Year.of(1983), "white", new BigDecimal(9990));
        CarListing carListing2 = new CarListing(1l, "c2", "Fiat", "Uno",
                110l, Year.of(1990), "black", new BigDecimal(7990));

        Mockito.doReturn(110l).when(carListingUtil).convertPsToKw(150l);
        Mockito.doReturn("Renault").when(carListingUtil).parseMake("Renault/4L");
        Mockito.doReturn("4L").when(carListingUtil).parseModel("Renault/4L");
        Mockito.doReturn("Fiat").when(carListingUtil).parseMake("Fiat/Uno");
        Mockito.doReturn("Uno").when(carListingUtil).parseModel("Fiat/Uno");
        Mockito.doReturn(1l).when(carListingsServiceSpy).saveOrUpdateListing(carListing1);
        Mockito.doReturn(2l).when(carListingsServiceSpy).saveOrUpdateListing(carListing2);

        carListingsServiceSpy.saveCarListings(1l, carListingCsvDtoList);

        verify(carListingsServiceSpy, times(2)).saveOrUpdateListing(any(CarListing.class));
    }

    @Test
    public void shouldDoNothingEmptyCsv() {
        List<CarListingCsvDto> carListingCsvDtoList = new ArrayList();
        carListingsServiceSpy.saveCarListings(1l, carListingCsvDtoList );
        verify(carListingsServiceSpy, never()).saveOrUpdateListing(any(CarListing.class));
    }

    @Test
    public void shouldSaveJsonList() {
        CarListingJsonDto carListingDto1 = new CarListingJsonDto("c1", "Renault", "4L",
                110l, Year.of(1983), "white", new BigDecimal(9990));
        CarListingJsonDto carListingDto2 = new CarListingJsonDto("c2", "Fiat", "Uno",
                110l, Year.of(1990), "black", new BigDecimal(7990));
        List<CarListingJsonDto> carListingJsonDtoList = Arrays.asList(carListingDto1, carListingDto2);

        CarListing carListing1 = new CarListing(null, "c1", "Renault", "4L",
                110l, Year.of(1983), "white", new BigDecimal(9990));
        CarListing carListing2 = new CarListing(null, "c2", "Fiat", "Uno",
                110l, Year.of(1990), "black", new BigDecimal(7990));

        Mockito.doReturn(1l).when(carListingsServiceSpy).saveOrUpdateListing(carListing1);
        Mockito.doReturn(2l).when(carListingsServiceSpy).saveOrUpdateListing(carListing2);

        carListingsServiceSpy.saveCarListings(carListingJsonDtoList);

        verify(carListingsServiceSpy, times(2)).saveOrUpdateListing(any(CarListing.class));
    }

    @Test
    public void shouldDoNothingEmptyJson() {
        List<CarListingJsonDto> carListingJsonDtoList = new ArrayList();
        carListingsServiceSpy.saveCarListings(carListingJsonDtoList);
        verify(carListingsServiceSpy, never()).saveOrUpdateListing(any(CarListing.class));
    }
}
