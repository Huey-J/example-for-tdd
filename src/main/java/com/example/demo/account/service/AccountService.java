package com.example.demo.account.service;

import com.example.demo.account.controller.AccountCreateRequestDTO;
import com.example.demo.account.controller.AccountTransferRequestDTO;
import com.example.demo.account.controller.AccountTransferResponseDTO;

public interface AccountService {

  void create(AccountCreateRequestDTO requestDTO);

  AccountTransferResponseDTO transfer(AccountTransferRequestDTO requestDTO);

}
