package com.example.demo.v2.account.controller;

public record CreateAccountResponseDtoV2(
    Long id,
    String owner,
    String accountNumber,
    int balance
) {

}
