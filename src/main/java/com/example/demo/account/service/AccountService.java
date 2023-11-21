package com.example.demo.account.service;

import com.example.demo.account.controller.CreateAccountRequestDto;
import com.example.demo.account.controller.CreateAccountResponseDto;
import com.example.demo.account.exception.AccountException;
import com.example.demo.account.repository.Account;
import com.example.demo.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Transactional
  public CreateAccountResponseDto create(CreateAccountRequestDto requestDto) {

    if (accountRepository.existsByAccountNumber(requestDto.accountNumber())) {
      throw new AccountException("이미 존재하는 계좌번호입니다.");
    }

    Account account = new Account();
    account.setOwner(requestDto.owner());
    account.setAccountNumber(requestDto.accountNumber());
    account.setPassword(requestDto.password());
    account.setBalance(0);

    Account savedAccount = accountRepository.save(account);

    return new CreateAccountResponseDto(
        savedAccount.getId(),
        savedAccount.getOwner(),
        savedAccount.getAccountNumber(),
        savedAccount.getBalance()
    );
  }
}