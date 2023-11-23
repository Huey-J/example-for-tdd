package com.example.demo.v2.account.controller;

import com.example.demo.v2.account.service.AccountServiceV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/account")
public class AccountControllerV2 {

  private final AccountServiceV2 accountService;

  @PostMapping
  public ResponseEntity create(@RequestBody @Valid CreateAccountRequestDtoV2 requestDto) {
    CreateAccountResponseDtoV2 responseDto = accountService.create(requestDto);
    return ResponseEntity.ok(responseDto);
  }

}
