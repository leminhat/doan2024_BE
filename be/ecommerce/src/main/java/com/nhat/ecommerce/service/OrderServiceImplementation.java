package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.*;
import com.nhat.ecommerce.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImplementation implements OrderService{

    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;



    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);



    @Autowired
    public OrderServiceImplementation(CartService cartService, ProductService productService
            , AddressRepository addressRepository, UserRepository userRepository, OrderItemRepository orderItemRepository
            , OrderRepository orderRepository, ProductRepository productRepository){


        this.cartService = cartService;;
        this.productService = productService;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {

        Address address = shippingAddress;
        System.out.println("shippingAddress " +shippingAddress.getId());

        if (shippingAddress.getId() == null) {
            System.out.println("da vao day" +shippingAddress.getId());
            shippingAddress.setUser(user);
            address = addressRepository.save(shippingAddress);
            user.getAddress().add(address);
            userRepository.save(user);
        }


        Order createOrder = new Order();

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();



        for (CartItem item:cart.getCartItems()){
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());

//            for (Size size : item.getProduct().getSizes()) {
//                if (size.getName().equals(item.getSize())) {
//                    size.setQuantity(size.getQuantity() - item.getQuantity());
//                }
//            }

            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            orderItem.setOrder(createOrder);


            OrderItem createOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createOrderItem);
        }

//      Order createOrder = new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setToltalDiscountedPrice(cart.getTotalDiscountedPrice());
        createOrder.setDiscounte(cart.getDiscounte());
        createOrder.setTotalItem(cart.getTotalItem());

        createOrder.setShippingAddress(shippingAddress);
        createOrder.setCreateAt(LocalDateTime.now());
        createOrder.setOrderStatus("PENDING");
        createOrder.getPaymentDetails().setStatus("PENDING");
        createOrder.setCreateAt(LocalDateTime.now());

        Order saveOrder = orderRepository.save(createOrder);
        for(OrderItem item : orderItems){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);

        }





        return saveOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById(orderId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new OrderException("Order does not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders = orderRepository.getUserOrders(userId);
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return orderRepository.save(order);
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERY");
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {

        Order order = findOrderById(orderId);
        if(!order.getOrderStatus().equals("CANCELLED")) {


            for (OrderItem item : order.getOrderItems()) {
                System.out.println("da vao day 2");
                for (Size size : item.getProduct().getSizes()) {
                    System.out.println("size name " + size.getName());
                    if (size.getName().equals(item.getSize())) {
                        System.out.println("quantity: " + size.getQuantity());
                        size.setQuantity(size.getQuantity() + item.getQuantity());
                        System.out.println("quantitysau: " + size.getQuantity());

                    }
                    productRepository.save(item.getProduct());
                }
            }


            order.setOrderStatus("CANCELLED");
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order deleteOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);

//        for(OrderItem item : order.getOrderItems()){
//            for (Size size : item.getProduct().getSizes()) {
//                if (size.getName().equals(item.getSize())) {
//                    size.setQuantity(size.getQuantity() + item.getQuantity());
//                }
//            }
//        }

        orderRepository.deleteById(orderId);
        return order;
    }
}
