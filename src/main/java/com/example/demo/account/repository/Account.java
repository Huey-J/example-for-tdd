package com.example.demo.account.repository;

import com.example.demo.account.controller.CreateAccountRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {

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

}
