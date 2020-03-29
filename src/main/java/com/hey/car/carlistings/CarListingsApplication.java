package com.hey.car.carlistings;

import com.hey.car.carlistings.rest.converter.CsvToListingsHttpMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.Arrays;

@SpringBootApplication
public class CarListingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarListingsApplication.class, args);
	}
}
