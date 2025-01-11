package com.leminhat.DataHandle.repository;

import com.leminhat.DataHandle.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    Revenue findById(long id);

    @Query("SELECT r.createAt AS reportDate, SUM(r.toltalDiscountedPrice) AS totalSales " +
            "FROM Revenue r " +
            "WHERE r.createAt BETWEEN :startDate AND :endDate " +
            "GROUP BY r.createAt " +
            "ORDER BY r.createAt ASC")
    List<Object[]> findSalesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);



}
