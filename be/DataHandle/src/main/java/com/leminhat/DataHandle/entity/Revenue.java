package com.leminhat.DataHandle.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="revenue")
public class Revenue {

    @Id
    private Long id;

    private Long totalPrice;

    @Column(name = "toltal_discounted_price")
    private Long toltalDiscountedPrice;

    private Long discounte;

    private int totalItem;

    @Column(name = "create_at")
    private LocalDate createAt;

    public Revenue() {}

    public Revenue(Long id, Long totalPrice, Long toltalDiscountedPrice, Long discounte, int totalItem, LocalDate createAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.toltalDiscountedPrice = toltalDiscountedPrice;
        this.discounte = discounte;
        this.totalItem = totalItem;
        this.createAt = createAt;
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

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
