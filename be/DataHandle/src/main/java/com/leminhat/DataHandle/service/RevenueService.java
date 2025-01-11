package com.leminhat.DataHandle.service;

import com.leminhat.DataHandle.entity.Revenue;
import com.leminhat.DataHandle.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    public List<Object[]> getSalesForLast7Days() {

        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysAgo = currentDate.minusDays(7);


        return revenueRepository.findSalesByDateRange(sevenDaysAgo, currentDate);
    }
}
