package com.example.demo.v2.account.repository;

import com.example.demo.v2.account.controller.CreateAccountRequestDtoV2;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "account_2")
public class AccountV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;
  @Column(nullable = false)
  private String owner;
  @Column(nullable = false, unique = true)
  private String accountNumber;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private int balance;

  public static AccountV2 create(CreateAccountRequestDtoV2 dto) {
    AccountV2 account = new AccountV2();
    account.owner = dto.owner();
    account.accountNumber = dto.accountNumber();
    account.password = dto.password();
    account.balance = 0;
    return account;
  }

}
