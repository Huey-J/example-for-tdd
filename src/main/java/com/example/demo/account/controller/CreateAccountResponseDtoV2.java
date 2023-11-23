package com.example.demo.account.controller;

public record CreateAccountResponseDtoV2(
    Long id,
    String owner,
    String accountNumber,
    int balance
) {

}
