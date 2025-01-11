package com.leminhat.DataHandle.repository;

import com.leminhat.DataHandle.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
