package com.example.demo.account.repository.jpa;

import com.example.demo.account.domain.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;
  @Column(nullable = false)
  private String accountNumber;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private String owner;
  private String alias;

  public static AccountEntity from(Account account) {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.id = account.id();
    accountEntity.accountNumber = account.accountNumber();
    accountEntity.password = account.password();
    accountEntity.owner = account.owner();
    accountEntity.alias = account.alias();
    return accountEntity;
  }

}
