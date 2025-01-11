package com.leminhat.DataHandle.controller;

import com.leminhat.DataHandle.entity.Revenue;
import com.leminhat.DataHandle.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/api/sales/last7days")
    public List<Object[]> getSalesForLast7Days() {
        return revenueService.getSalesForLast7Days();
    }




}
