package com.hey.car.carlistings.repository;

import com.hey.car.carlistings.model.CarListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarListingRepository extends JpaRepository<CarListing, Long> {
    List<CarListing> findByDealerIdAndCode(Long dealerId, String code);
}
