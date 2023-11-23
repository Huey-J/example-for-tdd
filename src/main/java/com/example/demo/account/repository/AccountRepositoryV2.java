package com.example.demo.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryV2 extends JpaRepository<AccountV2, Long> {

  boolean existsByAccountNumber(String accountNumber);
}
