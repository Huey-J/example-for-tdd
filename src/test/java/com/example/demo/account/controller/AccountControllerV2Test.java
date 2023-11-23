package com.example.demo.account.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.v2.account.controller.AccountControllerV2;
import com.example.demo.v2.account.controller.CreateAccountResponseDtoV2;
import com.example.demo.v2.account.exception.AccountExceptionV2;
import com.example.demo.v2.account.exception.GlobalExceptionHandlerV2;
import com.example.demo.v2.account.service.AccountServiceV2;
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
public class AccountControllerV2Test {

  private MockMvc mockMvc;

  @Mock
  private AccountServiceV2 accountService;
  @InjectMocks
  private AccountControllerV2 accountController;

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(accountController)
        .setControllerAdvice(new GlobalExceptionHandlerV2())
        .build();
  }

  @Nested
  class create {

    private final String API_PATH = "/api/v2/account";

    @Test
    void 계좌_추가_성공_시_200과_생성된_데이터를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "owner": "홍길동",
            "accountNumber": "2023-1234-567890",
            "password": "1234"
          }
          """;
      doReturn(new CreateAccountResponseDtoV2(1l, "홍길동", "2023-1234-567890", 0))
          .when(accountService).create(any());

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(1l))
          .andExpect(jsonPath("$.owner").value("홍길동"))
          .andExpect(jsonPath("$.accountNumber").value("2023-1234-567890"))
          .andExpect(jsonPath("$.balance").value(0));
    }

    @Test
    void 요청한_계좌번호가_이미_존재할_경우_400과_이미_존재한다는_문구를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "owner": "홍길동",
            "accountNumber": "2023-1234-567890",
            "password": "1234"
          }
          """;
      doThrow(new AccountExceptionV2("이미 존재하는 계좌번호입니다.")).when(accountService).create(any());

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("이미 존재하는 계좌번호입니다."));
    }

    @Test
    void 계좌번호가_비어있을_경우_400과_잘못된_요청에_대한_문구를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "owner": "홍길동",
            "accountNumber": "",
            "password": "1234"
          }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 비밀번호가_4자리_미만일_경우_400과_잘못된_요청에_대한_문구를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "owner": "홍길동",
            "accountNumber": "2023-1234-567890",
            "password": "12"
          }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 비밀번호가_6자리를_초과할_경우_400과_잘못된_요청에_대한_문구를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "owner": "홍길동",
            "accountNumber": "2023-1234-567890",
            "password": "12345678"
          }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

    @Test
    void 계좌_주인_이름이_null일_경우_400과_잘못된_요청에_대한_문구를_반환한다() throws Exception {
      // given
      String requestDto = """
          {
            "accountNumber": "2023-1234-567890",
            "password": "12345678"
          }
          """;

      // when
      ResultActions result = mockMvc.perform(
          MockMvcRequestBuilders
              .post(API_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .content(requestDto)
      );

      // then
      result.andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").value("잘못된 입력입니다."));
    }

  }

}
