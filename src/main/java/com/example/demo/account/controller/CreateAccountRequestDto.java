package com.example.demo.account.controller;

public record CreateAccountRequestDto (
    String owner,
    String accountNumber,
    String password
){

}
