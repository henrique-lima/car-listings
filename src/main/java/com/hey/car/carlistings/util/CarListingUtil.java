package com.hey.car.carlistings.util;

import org.springframework.stereotype.Component;

@Component
public class CarListingUtil {

    private static final Double PS_CONVERTION_FACTOR = 0.73549875;

    public Long convertPsToKw(Long powerPs) {
        return Math.round(PS_CONVERTION_FACTOR * powerPs);
    }
}
