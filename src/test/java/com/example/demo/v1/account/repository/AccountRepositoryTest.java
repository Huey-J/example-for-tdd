package com.example.demo.v1.account.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.example.demo.v1.account.domain.Account;
import com.example.demo.v1.account.repository.AccountRepositoryImpl;
import com.example.demo.v1.account.repository.jpa.AccountJpaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

  @Mock
  private AccountJpaRepository accountJpaRepository;
  @InjectMocks
  private AccountRepositoryImpl accountRepository;

  @Nested
  class save {

    @Test
    void save_메소드를_호출한다() {
      // given
      Account account = Account.builder()
          .accountNumber("1111-15342-358923")
          .password("abc123")
          .owner("홍길동")
          .alias("홍길동의 계좌")
          .build();
      doReturn(null).when(accountJpaRepository).save(any());

      // when
      accountRepository.save(account);

      // then
      verify(accountJpaRepository, atLeastOnce()).save(any());
    }
  }

}
