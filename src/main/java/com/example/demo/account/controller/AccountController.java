package com.example.demo.account.controller;

import com.example.demo.account.exception.AccountException;
import com.example.demo.account.service.AccountService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
  public ResponseEntity create(@RequestBody CreateAccountRequestDto requestDto) {
    // 요청값 검증 - 필수값
    if (!StringUtils.hasText(requestDto.accountNumber()) ||
        !StringUtils.hasText(requestDto.owner()) ||
        !StringUtils.hasText(requestDto.password())) {
      return ResponseEntity.badRequest().body(Map.of("message", "잘못된 입력입니다."));
    }

    // 요청값 검증 - 비밀번호 형식
    if (!(4 <= requestDto.password().length() && requestDto.password().length() <= 6)) {
      return ResponseEntity.badRequest().body(Map.of("message", "잘못된 입력입니다."));
    }

    try {
      // 비즈니스 로직 수행
      CreateAccountResponseDto responseDto = accountService.create(requestDto);
      return ResponseEntity.ok(responseDto);
    } catch (AccountException e) {
      // 실패 시 예외 메시지 반환
      return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }
  }

}
