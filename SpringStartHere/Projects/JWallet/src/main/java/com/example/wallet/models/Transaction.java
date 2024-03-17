package com.example.wallet.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("TRANSACTIONS")
public class Transaction {
    @Id
    private Long id;

    private Long senderId;

    private Long receiverId;

    private BigDecimal amount;

    @CreatedDate
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(Long senderId, Long receiverId, BigDecimal amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Transaction(Long id, Long senderId, Long receiverId, BigDecimal amount) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
}