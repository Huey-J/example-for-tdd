package com.example.demo.account.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.account.controller.CreateAccountRequestDtoV2;
import com.example.demo.account.controller.CreateAccountResponseDtoV2;
import com.example.demo.account.exception.AccountExceptionV2;
import com.example.demo.account.repository.AccountV2;
import com.example.demo.account.repository.AccountRepositoryV2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceV2Test {

  @Mock
  private AccountRepositoryV2 accountRepository;
  @InjectMocks
  private AccountServiceV2 accountService;

  @Nested
  class create {
    private final String requestedOwner = "홍길동";
    private final String requestedAccountNumber = "2023-1234-567890";
    private final String requestedPassword = "1234";

    private final CreateAccountRequestDtoV2 requestDTO =
        new CreateAccountRequestDtoV2(requestedOwner, requestedAccountNumber, requestedPassword);

    @Test
    void 요청한_계좌번호가_존재하지_않을_경우_새로_추가된_데이터를_반환한다() {
      // given
      AccountV2 accountMock = mock(AccountV2.class);
      when(accountMock.getId()).thenReturn(1l);
      when(accountMock.getOwner()).thenReturn(requestDTO.owner());
      when(accountMock.getAccountNumber()).thenReturn(requestDTO.accountNumber());
      when(accountMock.getBalance()).thenReturn(0);

      doReturn(accountMock).when(accountRepository).save(any());

      doReturn(false).when(accountRepository).existsByAccountNumber(requestedAccountNumber);

      // when
      CreateAccountResponseDtoV2 responseDto = accountService.create(requestDTO);

      // then
      verify(accountRepository, times(1)).save(any(AccountV2.class));

      assertThat(responseDto.id()).isEqualTo(1l);
      assertThat(responseDto.owner()).isEqualTo("홍길동");
      assertThat(responseDto.accountNumber()).isEqualTo("2023-1234-567890");
      assertThat(responseDto.balance()).isEqualTo(0);
    }

    @Test
    void 요청한_계좌번호가_이미_존재할_경우_예외를_발생시킨다() {
      // given
      doReturn(true).when(accountRepository).existsByAccountNumber(requestedAccountNumber);

      // when
      Throwable exception = assertThrows(AccountExceptionV2.class, () -> {
        accountService.create(requestDTO);
      });

      // then
      verify(accountRepository, never()).save(any());
      assertThat(exception.getMessage()).isEqualTo("이미 존재하는 계좌번호입니다.");
    }
  }

}
