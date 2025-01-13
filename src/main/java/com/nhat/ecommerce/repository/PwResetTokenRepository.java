package com.nhat.ecommerce.repository;

import com.nhat.ecommerce.model.PwResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwResetTokenRepository extends JpaRepository<PwResetToken, Long> {
    PwResetToken findByToken(String token);
}
