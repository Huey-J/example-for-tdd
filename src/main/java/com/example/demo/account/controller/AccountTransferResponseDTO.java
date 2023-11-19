package com.example.demo.account.controller;

import java.math.BigDecimal;

public record AccountTransferResponseDTO(
    BigDecimal fromAccountBalance,
    String toAccountOwner
) {

}
