package com.leminhat.DataHandle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leminhat.DataHandle.dto.DailyReport;
import com.leminhat.DataHandle.dto.OrderDTO;
import com.leminhat.DataHandle.entity.Revenue;
import com.leminhat.DataHandle.repository.OrderRepository;
import com.leminhat.DataHandle.repository.RevenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private RevenueRepository revenueRepository;

    public void handleCreate(String rawData) throws JsonProcessingException {
        OrderDTO orderDTOAfter = objectMapper.readValue(rawData, OrderDTO.class);
        Revenue revenue = revenueRepository.findById(orderDTOAfter.getId());
        if(revenue == null){
            revenue = new Revenue();
        }
        revenue.setId(orderDTOAfter.getId());
        revenue.setDiscounte(orderDTOAfter.getDiscounte());
        revenue.setTotalPrice(orderDTOAfter.getTotalPrice());
        revenue.setToltalDiscountedPrice(orderDTOAfter.getToltalDiscountedPrice());
        revenue.setTotalItem(orderDTOAfter.getTotalItem());
        revenue.setCreateAt(LocalDate.now());
        revenueRepository.save(revenue);
    }

    public void handleUpdate(String beforeNode,String afterNode) throws JsonProcessingException {

        OrderDTO orderDTOBefore = objectMapper.readValue(beforeNode, OrderDTO.class);
        OrderDTO orderDTOAfter = objectMapper.readValue(afterNode, OrderDTO.class);

        Revenue revenue = revenueRepository.findById(orderDTOAfter.getId());
        if(revenue == null){
            revenue = new Revenue();
        }
        if(orderDTOAfter.getStatus().equalsIgnoreCase("PLACED")) {
            revenue.setId(orderDTOAfter.getId());
            revenue.setDiscounte(orderDTOAfter.getDiscounte());
            revenue.setTotalPrice(orderDTOAfter.getTotalPrice());
            revenue.setToltalDiscountedPrice(orderDTOAfter.getToltalDiscountedPrice());
            revenue.setTotalItem(orderDTOAfter.getTotalItem());
            revenue.setCreateAt(LocalDate.now());
            revenueRepository.save(revenue);
        } else if (orderDTOAfter.getStatus().equalsIgnoreCase("CANCELLED")) {
            revenueRepository.deleteById(orderDTOAfter.getId());
            
        }
    }

}
