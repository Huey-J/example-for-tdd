package com.example.demo.v1.account.service;

import com.example.demo.v1.account.controller.AccountCreateRequestDTO;
import com.example.demo.v1.account.controller.AccountTransferRequestDTO;
import com.example.demo.v1.account.controller.AccountTransferResponseDTO;
import com.example.demo.v1.account.domain.Account;
import com.example.demo.v1.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public void create(AccountCreateRequestDTO requestDTO) {
    Account account = Account.builder()
        .accountNumber(requestDTO.accountNumber())
        .password(requestDTO.password())
        .owner(requestDTO.owner())
        .alias(requestDTO.alias())
        .build();

    accountRepository.save(account);
  }

  @Override
  public AccountTransferResponseDTO transfer(AccountTransferRequestDTO requestDTO) {
    return null;
  }
}
