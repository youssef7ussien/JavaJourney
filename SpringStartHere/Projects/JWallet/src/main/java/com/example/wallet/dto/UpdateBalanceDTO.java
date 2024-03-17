package com.example.wallet.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalanceDTO {
    private Long userId;
    private BigDecimal newBalance;
}
