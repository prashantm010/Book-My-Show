package com.example.models;

import com.example.enums.PaymentMode;
import com.example.enums.PaymentStatus;
import com.example.utils.IdGenerator;

import java.time.LocalDateTime;

public class Payment {
    String id;
    PaymentMode paymentMode;
    Double amount;
    PaymentStatus paymentStatus;
    LocalDateTime paymentDate;

    public Payment(PaymentMode paymentMode, Double amount, PaymentStatus paymentStatus) {
        this.id = IdGenerator.getId();
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = LocalDateTime.now();
    }
}
