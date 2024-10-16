package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.response.ApiResponse;
import com.nhat.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmOrderHandler(@PathVariable Long orderId
            , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.confirmedOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderId
    , @RequestHeader("Authorization") String jwt) throws OrderException {

        Order order = orderService.shippedOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> DeliverOrderHandler (@PathVariable Long orderId
        , @RequestHeader("Authorization") String jwt) throws OrderException {

        Order order = orderService.deliveredOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> CancleOrderHandler (@PathVariable Long orderId
            ,@RequestHeader("Authorization") String jwt) throws OrderException {

        Order order = orderService.cancledOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> DeleteOrderHandler (@PathVariable Long orderId
            ,@RequestHeader("Authorization") String jwt) throws OrderException {

        Order order = orderService.deliveredOrder(orderId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Order deleted Successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);

    }




}
