package com.nhat.ecommerce.service;

import com.nhat.ecommerce.model.PwResetToken;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.PwResetTokenRepository;
import com.nhat.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PwResetService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PwResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void initiatePasswordReset(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Email không tồn tại.");
        }

        // Tạo mã xác thực
        String token = UUID.randomUUID().toString();
        Date expirationDate = new Date(System.currentTimeMillis() + 360000); // 6 phut

        // Lưu token vào cơ sở dữ liệu
        PwResetToken passwordResetToken = new PwResetToken (token, email, expirationDate);
        tokenRepository.save(passwordResetToken);

        // Gửi email đặt lại mật khẩu
        emailService.sendPasswordResetEmail(email, token);
    }

    public void resetPassword(String token, String newPassword) {
        PwResetToken passwordResetToken = tokenRepository.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Token không hợp lệ hoặc đã hết hạn.");
        }

        User user = userRepository.findByEmail(passwordResetToken.getEmail());
        user.setPassword(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu
        userRepository.save(user);

        // Xóa token sau khi đã sử dụng
        tokenRepository.delete(passwordResetToken);
    }
}
