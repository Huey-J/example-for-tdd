package com.example.demo.account.repository;

import com.example.demo.account.domain.Account;
import com.example.demo.account.repository.jpa.AccountEntity;
import com.example.demo.account.repository.jpa.AccountJpaRepository;
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
