package com.hey.car.carlistings.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hey.car.carlistings.model.dto.CarListingJsonDto;
import com.hey.car.carlistings.repository.CarListingRepository;
import com.hey.car.carlistings.service.CarListingsService;
import com.hey.car.carlistings.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarListingsTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarListingsService carListingsService;
    @Autowired
    private CarListingRepository carListingRepository;

    @AfterEach
    public void cleanup() {
        carListingRepository.deleteAll();
    }

    @Test
    public void createListingsFromCsvTest() throws Exception {
        String input = TestUtil.getDataFromFile("/static/listings.csv");

        mockMvc.perform(
                post("/upload_csv/1")
                    .contentType(new MediaType("text", "csv"))
                    .content(input))
                .andExpect(status().is(201));

        List<CarListingJsonDto> carListingsList = carListingsService.searchListings();

        assertEquals(3, carListingsList.size());
        assertEquals(1l, carListingsList.get(0).getDealerId());
        assertEquals("1", carListingsList.get(0).getCode());
        assertEquals("mercedes", carListingsList.get(0).getMake());
        assertEquals("a 180", carListingsList.get(0).getModel());
        assertEquals("2", carListingsList.get(1).getCode());
        assertEquals("audi", carListingsList.get(1).getMake());
        assertNull(carListingsList.get(1).getModel());
        assertEquals("3", carListingsList.get(2).getCode());
        assertEquals(Year.of(2018), carListingsList.get(2).getYear());
        assertEquals("green", carListingsList.get(2).getColor());
    }

    @Test
    public void createListingsFromJsonTest() throws Exception {
        String input = TestUtil.getDataFromFile("/static/listings.json");

        mockMvc.perform(
                post("/vehicle_listings")
                        .contentType(new MediaType("application", "json"))
                        .content(input))
                .andExpect(status().is(201));

        List<CarListingJsonDto> carListingsList = carListingsService.searchListings();

        assertEquals(1, carListingsList.size());
        assertEquals("a", carListingsList.get(0).getCode());
        assertEquals("renault", carListingsList.get(0).getMake());
        assertEquals("megane", carListingsList.get(0).getModel());
        assertEquals(132l, carListingsList.get(0).getKw());
        assertEquals(Year.of(2014), carListingsList.get(0).getYear());
        assertEquals("red", carListingsList.get(0).getColor());
        assertTrue(carListingsList.get(0).getPrice().intValue() == 13990);
    }

    @Test
    public void searchListingsTest() throws Exception {
        CarListingJsonDto carListing1 = new CarListingJsonDto("c1", "Renault", "Clio",
                100l, Year.of(2000), "white", BigDecimal.valueOf(12000l));
        CarListingJsonDto carListing2 = new CarListingJsonDto("c2", "Renault", "Megane",
                130l, Year.of(2000), "white", BigDecimal.valueOf(15000l));
        CarListingJsonDto carListing3 = new CarListingJsonDto("c3", "Mercedes", "Benz",
                100l, Year.of(2010), "black", BigDecimal.valueOf(30000l));
        List<CarListingJsonDto> carListingsInput = Arrays.asList(carListing1, carListing2, carListing3);

        carListingsService.saveCarListings(carListingsInput);

        String response = mockMvc.perform(
                get("/search")
                        .contentType(new MediaType("application", "json"))
                        .param("make", "Renault")
                        .param("year", "2000")
                        .param("color", "white"))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        List<CarListingJsonDto> carListingsOutput = objectMapper.readValue(response, new TypeReference<List<CarListingJsonDto>>() { });

        assertEquals(2, carListingsOutput.size());
        assertEquals("c1", carListingsOutput.get(0).getCode());
        assertEquals("Renault", carListingsOutput.get(0).getMake());
        assertEquals("Clio", carListingsOutput.get(0).getModel());
        assertEquals("c2", carListingsOutput.get(1).getCode());
        assertEquals("white", carListingsOutput.get(1).getColor());
        assertEquals(130l, carListingsOutput.get(1).getKw());
    }
}
