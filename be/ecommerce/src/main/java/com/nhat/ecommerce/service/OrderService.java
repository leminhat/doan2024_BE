package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.Address;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public Order createOrder(User user, Address shippingaddress);

    public  Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public Order deleteOrder(Long orderId) throws OrderException;


}
