package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.config.PaymentConfig;
import com.nhat.ecommerce.exception.OrderException;
import com.nhat.ecommerce.model.Order;
import com.nhat.ecommerce.response.ApiResponse;
import com.nhat.ecommerce.service.OrderService;
import com.nhat.ecommerce.service.UserService;
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


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @PostMapping("/create_payment")
    public String createPayment(@RequestBody Order order) throws UnsupportedEncodingException {

        long amount = order.getToltalDiscountedPrice()*100;

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

        return paymentUrl;
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
            ApiResponse res = new ApiResponse();
            res.setMessage("Payment Fail");
            res.setStatus(false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

    }



}
