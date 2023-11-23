package com.example.demo.v1.account.domain;

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
