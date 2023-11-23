package com.example.demo.v2.account.service;

import com.example.demo.v2.account.controller.CreateAccountRequestDtoV2;
import com.example.demo.v2.account.controller.CreateAccountResponseDtoV2;
import com.example.demo.v2.account.exception.AccountExceptionV2;
import com.example.demo.v2.account.repository.AccountV2;
import com.example.demo.v2.account.repository.AccountRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountServiceV2 {

  private final AccountRepositoryV2 accountRepository;

  @Transactional
  public CreateAccountResponseDtoV2 create(CreateAccountRequestDtoV2 requestDto) {

    if (accountRepository.existsByAccountNumber(requestDto.accountNumber())) {
      throw new AccountExceptionV2("이미 존재하는 계좌번호입니다.");
    }

    AccountV2 savedAccount = accountRepository.save(AccountV2.create(requestDto));

    return new CreateAccountResponseDtoV2(
        savedAccount.getId(),
        savedAccount.getOwner(),
        savedAccount.getAccountNumber(),
        savedAccount.getBalance()
    );
  }
}