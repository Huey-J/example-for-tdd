package com.example.demo.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.account.controller.CreateAccountRequestDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

  @Autowired
  private AccountRepository accountRepository;

  @Nested
  class existsByAccountNumber {

    @Test
    void 계좌번호가_존재할_경우_true를_반환한다() {
      // given
      Account account = Account.create(
          new CreateAccountRequestDto("홍길동", "2023-1234-567890", "1234"));
      accountRepository.save(account);

      // when
      boolean result = accountRepository.existsByAccountNumber("2023-1234-567890");

      // then
      assertThat(result).isTrue();
    }

    @Test
    void 계좌번호가_존재하지_않을_경우_false를_반환한다() {
      // given
      Account account = Account.create(
          new CreateAccountRequestDto("홍길동", "2023-1234-567890", "1234"));
      accountRepository.save(account);

      // when
      boolean result = accountRepository.existsByAccountNumber("2023-1234-000000");

      // then
      assertThat(result).isFalse();
    }
  }

}
