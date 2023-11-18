package com.example.demo.account.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AccountService accountService;
  @InjectMocks
  private AccountController accountController;

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(accountController)
        .build();
  }

  @Nested
  class create {

    private final String API_PATH = "/api/v1/account";

    @Test
    void 계좌_추가_성공_시_200을_반환한다() {
    }

    @Test
    void 별칭을_입력하지_않아도_계좌_추가에_성공하여_200을_반환한다() {
    }

    @Test
    void 요청한_계좌번호가_이미_존재할_경우_400을_반환한다() {
    }

    @Test
    void 비밀번호가_4자리_미만일_경우_400을_반환한다() {
    }

    @Test
    void 비밀번호가_6자리를_초과할_경우_400을_반환한다() {
    }

    @Test
    void 계좌번호가_null일_경우_400을_반환한다() {
    }

    @Test
    void 비밀번호가_null일_경우_400을_반환한다() {
    }

    @Test
    void 계좌_주인_이름이_null일_경우_400을_반환한다() {
    }
  }

}
