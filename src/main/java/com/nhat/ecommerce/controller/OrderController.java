package com.nhat.ecommerce.controller;


import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.Address;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.service.OrderService;
import com.nhat.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress
            , @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.createOrder(user,shippingAddress);

        return new ResponseEntity<Order>(order,HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(
            @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long Id
            , @RequestHeader("Authorization") String jwt) throws UserException, OrderException {

        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.findOrderById(Id);


        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

}
