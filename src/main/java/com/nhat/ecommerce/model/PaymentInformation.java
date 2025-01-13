package com.nhat.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
public class PaymentInformation {

    @Column(name="cardholder_name")
    private String cardholderName;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="cvv")
    private String cvv;
}
