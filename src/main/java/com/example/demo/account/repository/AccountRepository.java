package com.example.demo.account.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {

  boolean existsByAccountNumber(String accountNumber);

  Account save(Account account);


}
