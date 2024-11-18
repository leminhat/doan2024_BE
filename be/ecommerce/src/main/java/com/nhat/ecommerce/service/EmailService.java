package com.nhat.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Đặt lại mật khẩu";
        String resetUrl = "http://localhost:3000/reset-password/" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu: " + resetUrl);
        emailSender.send(message);
    }
}
