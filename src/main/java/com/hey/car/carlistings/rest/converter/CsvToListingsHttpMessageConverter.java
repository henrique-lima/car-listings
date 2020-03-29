package com.hey.car.carlistings.rest.converter;

import com.hey.car.carlistings.model.dto.CarListingCsvDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvToListingsHttpMessageConverter
        extends AbstractHttpMessageConverter<List<CarListingCsvDto>> {

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
                .build();

        CsvToBean csvParser = new CsvToBeanBuilder(csvReader)
                .withType(CarListingCsvDto.class)
                .build();

        Iterator<CarListingCsvDto> iterator = csvParser.iterator();
        List<CarListingCsvDto> carListingCsvDtos = new ArrayList<>();

        iterator.forEachRemaining(cl ->
            carListingCsvDtos.add(cl)
        );

        return carListingCsvDtos;
    }

    @Override
    protected void writeInternal(List<CarListingCsvDto> carListings, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
    }
}
