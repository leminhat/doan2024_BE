package com.leminhat.DataHandle.dto;

import java.time.LocalDate;

public class DailyReport {
    private LocalDate date;
    private Long totalAmount;

    public DailyReport() {}

    public DailyReport(LocalDate date, Long totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
