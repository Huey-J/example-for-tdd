package com.example.demo.v1.account.repository;

import com.example.demo.v1.account.domain.Account;
import com.example.demo.v1.account.repository.jpa.AccountEntity;
import com.example.demo.v1.account.repository.jpa.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

  private final AccountJpaRepository accountJpaRepository;

  @Override
  public void save(Account account) {
    accountJpaRepository.save(AccountEntity.from(account));
  }
}
