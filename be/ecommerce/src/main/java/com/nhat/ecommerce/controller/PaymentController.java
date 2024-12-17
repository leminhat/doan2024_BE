package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.config.PaymentConfig;
import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.model.OrderItem;
import com.nhat.ecommerce.model.Size;
import com.nhat.ecommerce.repository.OrderRepository;
import com.nhat.ecommerce.repository.ProductRepository;
import com.nhat.ecommerce.response.ApiResponse;
import com.nhat.ecommerce.service.OrderService;
import com.nhat.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    @PostMapping("/create_payment")
    public ResponseEntity<ApiResponse> createPayment(@RequestBody Order order) throws UnsupportedEncodingException {

        System.out.println("da vao create Payment");


        long amount = order.getToltalDiscountedPrice()*100;

        for (OrderItem item : order.getOrderItems()) {

            for (Size size : item.getProduct().getSizes()) {

                System.out.println("name size" + size.getName());
                System.out.println("quantity size" + size.getQuantity());
                    if (size.getName().equals(item.getSize())) {
                        if(size.getQuantity() - item.getQuantity() < 0){
                            ApiResponse res = new ApiResponse();
                            res.setMessage("San pham khong du so luong vui long thu lai sau");
                            res.setStatus(false);
                            return new ResponseEntity<>(res, HttpStatus.OK);
                        }
                        size.setQuantity(size.getQuantity() - item.getQuantity());
                        System.out.println(size.getQuantity());
                    }
                }
            productRepository.save(item.getProduct());
        }



//        Order order1 = orderRepository.findById(order.getId());
//        order.setOrderStatus("PENDING");
//        orderRepository.save(order);


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            System.out.println("da vao day");
            try {
                cancelOrderIfNotPaid(order.getId());
            } catch (OrderException e) {
                throw new RuntimeException(e);
            }
        }, 1, TimeUnit.MINUTES);

        String orderID = String.valueOf(order.getId());

        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "VNBANK");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "other");

        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl+ orderID);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;

        ApiResponse res = new ApiResponse();
        res.setMessage(paymentUrl);
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);

    //        return paymentUrl;
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> verifyPayment(@RequestParam("order_id") Long orderId, @RequestBody Map<String, String> allParams) throws OrderException {


        System.out.println("orderId " + orderId);

        String vnp_ResponseCode =  allParams.get("vnp_ResponseCode");

        System.out.println("amount " + allParams.get("vnp_Amount"));
        System.out.println("vnp_ResponseCode " + vnp_ResponseCode);

        if (vnp_ResponseCode.equals("00")){
            Order order  = orderService.placedOrder(orderId);
            ApiResponse res = new ApiResponse();
            res.setMessage("Payment Successful");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            Order order  = orderService.cancledOrder(orderId);
            ApiResponse res = new ApiResponse();
            res.setMessage("Payment Fail");
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    }

    public void cancelOrderIfNotPaid(Long id) throws OrderException {
            Order order = orderService.findOrderById(id);

            if(order.getOrderStatus().equals("PENDING")){

                Order order1 = orderService.cancledOrder(id);
                System.out.println("Order1Status " + order1.getOrderStatus());

            }
    }



}
