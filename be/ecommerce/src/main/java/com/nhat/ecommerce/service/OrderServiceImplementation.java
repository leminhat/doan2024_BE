package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.Address;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;


    @Autowired
    public OrderServiceImplementation(CartRepository cartRepository,
                                      CartService cartService, ProductService productService){

        this.cartRepository = cartRepository;
        this.cartService = cartService;;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingaddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
