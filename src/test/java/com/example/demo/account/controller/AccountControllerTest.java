package com.example.demo.account.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.account.exception.AccountException;
import com.example.demo.account.service.AccountService;
import com.example.demo.common.GlobalExceptionHandler;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
        .setControllerAdvice(new GlobalExceptionHandler())
        .build();
  }

  @Nested
  class create {

    private final String API_PATH = "/api/v1/account";

    @Test
    void 계좌_추가_성공_시_200을_반환한다() throws Exception {
      // given
      var requestDTO = """
              {
                "accountNumber": "1111-15342-358923",
                "password": "abc123",
                "owner": "홍길동",
                "alias": "홍길동의 계좌"
              }
          """;
      doNothing().when(accountService).create(any());

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isOk());
    }

    @Test
    void 별칭을_입력하지_않아도_계좌_추가에_성공하여_200을_반환한다() throws Exception {
      // given
      var requestDTO = """
              {
                "accountNumber": "1111-15342-358923",
                "password": "abc123",
                "owner": "홍길동"
              }
          """;
      doNothing().when(accountService).create(any());

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isOk());
    }

    @Test
    void 요청한_계좌번호가_이미_존재할_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
              {
                "accountNumber": "1111-15342-358923",
                "password": "abc123",
                "owner": "홍길동",
                "alias": "홍길동의 계좌"
              }
          """;
      doThrow(new AccountException("이미 존재하는 계좌번호입니다.")).when(accountService).create(any());

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("이미 존재하는 계좌번호입니다."));
    }

    @Test
    void 비밀번호가_4자리_미만일_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
              {
                "accountNumber": "1111-15342-358923",
                "password": "abc",
                "owner": "홍길동",
                "alias": "홍길동의 계좌"
              }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 비밀번호가_6자리를_초과할_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
              {
                "accountNumber": "1111-15342-358923",
                "password": "abc1234",
                "owner": "홍길동",
                "alias": "홍길동의 계좌"
              }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 계좌번호가_null일_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
            {
              "password": "abc123",
              "owner": "홍길동"
            }
        """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 비밀번호가_null일_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
            {
              "accountNumber": "1111-15342-358923",
              "owner": "홍길동"
            }
        """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 계좌_주인_이름이_null일_경우_400을_반환한다() throws Exception {
      // given
      var requestDTO = """
            {
              "accountNumber": "1111-15342-358923",
              "password": "abc123"
            }
        """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDTO)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }
  }

  @Nested
  class transfer {

    private final String API_PATH = "/api/v1/account/transfer";

    @Test
    void 계좌_이체_성공_시_200과_송금대상_게좌의_소유주와_잔액을_반환한다() {
    }

    @Test
    void 잔액이_부족한_경우_400을_반환한다() {
    }

    @Test
    void 송금대상_계좌가_존재하지_않는_경우_400을_반환한다() {
    }

    @Test
    void 계좌의_비밀번호가_틀린_경우_400을_반환한다() {
    }

    @Test
    void 송금액이_1원보다_작을_경우_400을_반환한다() {
    }

    @Test
    void 입금_계좌번호가_null인_경우_400을_반환한다() {
    }

    @Test
    void 출금_계좌번호가_null인_경우_400을_반환한다() {
    }
  }

}
