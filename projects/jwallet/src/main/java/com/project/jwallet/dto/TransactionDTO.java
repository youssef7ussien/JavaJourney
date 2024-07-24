package com.project.jwallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
