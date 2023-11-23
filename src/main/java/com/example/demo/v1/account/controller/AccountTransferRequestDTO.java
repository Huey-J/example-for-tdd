package com.example.demo.v1.account.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public record AccountTransferRequestDTO(
    @NotEmpty
    String fromAccountNumber,
    @NotEmpty
    String toAccountNumber,
    String password,
    @Min(1)
    BigDecimal amount
) {

}
