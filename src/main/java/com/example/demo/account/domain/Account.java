package com.example.demo.account.domain;

import lombok.Builder;

@Builder
public record Account(
    Long id,
    String accountNumber,
    String password,
    String owner,
    String alias
) {

}
