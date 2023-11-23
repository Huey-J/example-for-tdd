package com.example.demo.account.controller;

import com.example.demo.account.service.AccountServiceV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

  private final AccountServiceV2 accountService;

  @PostMapping
  public ResponseEntity create(@RequestBody @Valid CreateAccountRequestDtoV2 requestDto) {
    CreateAccountResponseDtoV2 responseDto = accountService.create(requestDto);
    return ResponseEntity.ok(responseDto);
  }

}
