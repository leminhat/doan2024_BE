package com.nhat.ecommerce.service;

import com.nhat.ecommerce.model.OrderItem;
import com.nhat.ecommerce.repository.OrderItemRepository;

public class OrderItemServiceImplementation implements OrderItemService {

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
