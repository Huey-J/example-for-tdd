package com.example.demo.v1.account.service;

import com.example.demo.v1.account.controller.AccountCreateRequestDTO;
import com.example.demo.v1.account.controller.AccountTransferRequestDTO;
import com.example.demo.v1.account.controller.AccountTransferResponseDTO;

public interface AccountService {

  void create(AccountCreateRequestDTO requestDTO);

  AccountTransferResponseDTO transfer(AccountTransferRequestDTO requestDTO);

}
