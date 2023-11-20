package com.example.demo.account.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AccountControllerTest {

  @Nested
  class create {

    private final String API_PATH = "/api/v1/account";

    @Test
    void 계좌_추가_성공_시_200과_생성된_데이터를_반환한다() {}

    @Test
    void 요청한_계좌번호가_이미_존재할_경우_400과_이미_존재한다는_문구를_반환한다() {}

    @Test
    void 계좌번호가_비어있을_경우_400과_잘못된_요청에_대한_문구를_반환한다() {}

    @Test
    void 비밀번호가_4자리_미만일_경우_400과_잘못된_요청에_대한_문구를_반환한다() {}

    @Test
    void 비밀번호가_6자리를_초과할_경우_400과_잘못된_요청에_대한_문구를_반환한다() {}

    @Test
    void 계좌_주인_이름이_null일_경우_400과_잘못된_요청에_대한_문구를_반환한다() {}
  }

}
