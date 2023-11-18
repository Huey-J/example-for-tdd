package com.example.demo.account.controller;

import com.example.demo.account.service.AccountService;
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

  private final AccountService accountService;

  @PostMapping
  public ResponseEntity<String> create(@RequestBody @Valid AccountCreateRequestDTO requestDTO) {
    accountService.create(requestDTO);
    return ResponseEntity.ok("success");
  }

}
