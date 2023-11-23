package com.example.demo.v1.account.controller;

import java.math.BigDecimal;

public record AccountTransferResponseDTO(
    BigDecimal fromAccountBalance,
    String toAccountOwner
) {

}
