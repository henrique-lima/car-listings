package com.hey.car.carlistings.rest.converter;

import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvToListingsHttpMessageConverter
        extends AbstractHttpMessageConverter<List<CarListingCsvDto>> {

    private Logger logger = LoggerFactory.getLogger(CsvToListingsHttpMessageConverter.class);

    public CsvToListingsHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected List<CarListingCsvDto> readInternal(Class<? extends List<CarListingCsvDto>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(httpInputMessage.getBody()))
                .withSkipLines(1)
                .build();

        List<CarListingCsvDto> carListingCsvDtos = new ArrayList<>();
        Iterator<String[]> iterator = csvReader.iterator();

        iterator.forEachRemaining(cl -> {
            CarListingCsvDto carListingCsvDto = parseToCarListingCsvDto(cl);
            if (carListingCsvDto != null) carListingCsvDtos.add(carListingCsvDto);
        });

        return carListingCsvDtos;
    }

    @Override
    protected void writeInternal(List<CarListingCsvDto> carListings, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private CarListingCsvDto parseToCarListingCsvDto(String[] carListingFields) {
        CarListingCsvDto carListingCsvDto = null;
        if (carListingFields.length == CarListingCsvDto.class.getDeclaredFields().length - 1) {
            try {
                String[] makeModel = carListingFields[1].split("/");
                carListingCsvDto = new CarListingCsvDto(
                        carListingFields[0],
                        makeModel[0],
                        makeModel.length > 1 ? makeModel[1] : null,
                        Long.parseLong(carListingFields[2].trim()),
                        Year.parse(carListingFields[3].trim()),
                        carListingFields[4],
                        new BigDecimal(carListingFields[5].trim())
                );
            } catch (IllegalArgumentException e) {
                logger.warn("Error parsing field. " + e.getMessage());
            }
        }
        return carListingCsvDto;
    }
}
