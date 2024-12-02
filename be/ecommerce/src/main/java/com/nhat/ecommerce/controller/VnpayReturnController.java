package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.config.PaymentConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VnpayReturnController {

//    public IPNResponse IPN(HttpServletRequest request)  {
//        try {
//            Map fields = new HashMap();
//            for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
//                String fieldKey = java.net.URLEncoder.encode(params.nextElement().toString(), StandardCharsets.US_ASCII.toString());
//                String fieldVal = java.net.URLEncoder.encode(request.getParameter(fieldKey), StandardCharsets.US_ASCII.toString());
//                if (fieldKey != null && StringUtils.hasText(fieldVal)) fields.put(fieldKey, fieldVal);
//            }
//            if (fields.containsKey("vnp_SecureHash")) fields.remove("vnp_SecureHash");
//            if (fields.containsKey("vnp_SecureHashType")) fields.remove("vnp_SecureHashType");
//            String secureHash = PaymentConfig.hashAllFields(fields);
//            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//            if (!secureHash.equals(vnp_SecureHash))
//                return IPNResponse.builder().RspCode("97").Message("Invalid Checksum").build();
//            String txnRef = request.getParameter("vnp_TxnRef");
//            OrderModel orderModel = orderRepository.findByIdentifyId(txnRef);
//            if (orderModel == null) return IPNResponse.builder().RspCode("01").Message("Order not Found").build();
//            if (orderModel.getTotalPrice() * 100 != Long.parseLong(request.getParameter("vnp_Amount")))
//                return IPNResponse.builder().RspCode("04").Message("Invalid Amount").build();
//            if (orderModel.getStatus().equals("Da thanh toan"))
//                return IPNResponse.builder().RspCode("02").Message("Order already confirmed").build();
//            TransactionModel transactionModel = new TransactionModel();
//            transactionModel.setBankCode(request.getParameter("vnp_BankCode"));
//            transactionModel.setBankTransactionNo(request.getParameter("vnp_BankTranNo"));
//            transactionModel.setIdentifyCode(request.getParameter("vnp_TransactionNo"));
//            transactionModel.setStatus("Thanh toan khong thanh cong");
//            orderModel.setStatus("Thanh toan khong thanh cong");
//            if (request.getParameter("vnp_ResponseCode").equals("00")) {
//                transactionModel.setStatus("Da thanh toan Vnpay thanh cong (vui long cho xac nhan)");
//                orderModel.setStatus("Da thanh toan Vnpay thanh cong (vui long cho xac nhan)");
//            }
//            transactionModel.setOrderModel(orderModel);
//            transactionModel.setPaymentModel(orderModel.getPaymentModel());
//            transactionRepository.save(transactionModel);
//            orderRepository.save(orderModel);
//            return IPNResponse.builder().RspCode("00").Message("Confirm Success").build();
//        }
//        catch (Exception exception){
//            return IPNResponse.builder().RspCode("99").Message("Unknown error").build();
//        }
//    }
}
