package com.example.demo.account.service;

import com.example.demo.account.controller.AccountCreateRequestDTO;
import com.example.demo.account.controller.AccountTransferRequestDTO;
import com.example.demo.account.controller.AccountTransferResponseDTO;
import com.example.demo.account.domain.Account;
import com.example.demo.account.repository.AccountRepository;
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
