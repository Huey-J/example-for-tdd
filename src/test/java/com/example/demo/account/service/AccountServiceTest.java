package com.example.demo.account.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.example.demo.account.controller.AccountCreateRequestDTO;
import com.example.demo.account.repository.AccountRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;
  @InjectMocks
  private AccountServiceImpl accountService;

  @Nested
  class create {

    @Test
    void 계좌_추가_성공_시_save메소드를_호출한다() {
      // given
      AccountCreateRequestDTO requestDTO = new AccountCreateRequestDTO(
          "1111-15342-358923", "abc123", "홍길동", "홍길동의 계좌");
      doNothing().when(accountRepository).save(any());

      // when
      accountService.create(requestDTO);

      // then
      verify(accountRepository, atLeastOnce()).save(any());
    }
  }

}
