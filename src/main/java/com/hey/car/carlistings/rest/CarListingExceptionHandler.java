package com.hey.car.carlistings.rest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.hey.car.carlistings.model.dto.CarListingErrorDto;
import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CarListingExceptionHandler {

    @ExceptionHandler(CsvException.class)
    ResponseEntity handleInvalidCsvException(CsvException e) {
        CarListingErrorDto errorResponse = new CarListingErrorDto(LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(JsonMappingException.class)
    ResponseEntity handleInvalidJsonException(JsonMappingException e) {
        CarListingErrorDto errorResponse = new CarListingErrorDto(LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }
}
