package com.hey.car.carlistings.repository;

import com.hey.car.carlistings.model.CarListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface CarListingRepository extends JpaRepository<CarListing, Long> {
    List<CarListing> findByDealerIdAndCode(Long dealerId, String code);
    @Query("SELECT cl FROM CarListing cl WHERE (:make is null or cl.make = :make) AND (:model is null or cl.model = :model)" +
            " AND (:year is null or cl.year = :year) AND (:color is null or cl.color = :color) ORDER BY cl.dealerId, cl.code")
    List<CarListing> findByMakeOrModelOrYearOrColor(@Param("make") String make, @Param("model") String model,
                                                    @Param("year") Year year, @Param("color") String color);
}
