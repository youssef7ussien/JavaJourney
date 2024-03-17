package com.example.wallet.models;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USERS")
public class User {
    @Id
    private Long id;

    private String username;

    private BigDecimal balance;

    public User() {
    }

    public User(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }

    public User(Long id, String username, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }
}