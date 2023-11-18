package com.example.demo.account.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AccountCreateRequestDTO(
    @NotEmpty
    String accountNumber,
    @NotEmpty @Size(min = 4, max = 6)
    String password,
    @NotEmpty
    String owner,
    String alias
) {

}
