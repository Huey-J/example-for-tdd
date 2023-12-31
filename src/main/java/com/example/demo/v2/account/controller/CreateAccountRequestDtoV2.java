package com.example.demo.v2.account.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateAccountRequestDtoV2(
    @NotEmpty
    String owner,
    @NotEmpty
    String accountNumber,
    @NotEmpty
    @NotEmpty @Size(min = 4, max = 6)
    String password
){

}
