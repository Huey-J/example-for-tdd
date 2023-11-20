package com.example.demo.account.controller;

public record CreateAccountResponseDto(
    Long id,
    String owner,
    String accountNumber,
    int balance
) {

}
