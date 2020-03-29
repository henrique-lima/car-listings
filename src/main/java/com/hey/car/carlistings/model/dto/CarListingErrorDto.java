package com.hey.car.carlistings.model.dto;

import java.time.LocalDateTime;

public class CarListingErrorDto {

    private LocalDateTime timestamp;
    private String message;

   public CarListingErrorDto(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
