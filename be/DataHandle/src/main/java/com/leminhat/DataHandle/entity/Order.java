package com.leminhat.DataHandle.entity;



import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long totalPrice;

    private Long toltalDiscountedPrice;

    private Long discounte;

    private String orderStatus;

    private int totalItem;

    private LocalDateTime createAt;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getToltalDiscountedPrice() {
        return toltalDiscountedPrice;
    }

    public void setToltalDiscountedPrice(Long toltalDiscountedPrice) {
        this.toltalDiscountedPrice = toltalDiscountedPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getDiscounte() {
        return discounte;
    }

    public void setDiscounte(Long discounte) {
        this.discounte = discounte;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}


