package com.nhnacademy.team4.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.AccountRegisterDTO;
import com.nhnacademy.team4.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("dev")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AccountRepository accountRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        accountRepository.save(Account.builder()
                .loginId("hwa")
                .status(AccountStatus.ACTIVE)
                .build());
        accountRepository.save(Account.builder()
                .loginId("withdraw account")
                .status(AccountStatus.WITHDRAW)
                .build());
        accountRepository.save(Account.builder()
                .loginId("inactive account")
                .status(AccountStatus.INACTIVE)
                .build());
    }

    @Test
    @Order(2)
    void getAccounts() throws Exception {
        mockMvc.perform(get("/account-api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].loginId", equalTo("hwa")));

    }

    @Test
    @Order(3)
    void getAccount() throws Exception {
        mockMvc.perform(get("/account-api/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("loginId", equalTo("hwa")));
    }

    @Test
    @Order(4)
    void getLogin() throws Exception {
        Account testAccount = Account.builder()
                .loginId("getLogin")
                .build();
        accountRepository.save(testAccount);

        mockMvc.perform(get("/account-api/accounts/login/{id}", testAccount.getLoginId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("accountId", equalTo(testAccount.getAccountId().intValue())));
    }

    @Test
    @Order(1)
    void createAccount() throws Exception {
        AccountRegisterDTO actual = new AccountRegisterDTO();
        actual.setLoginId("kim");
        actual.setPassword("password");
        actual.setEmail("nhnacademy@naver.com");

        mockMvc.perform(post("/account-api/accounts")
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loginId", equalTo("kim")));
    }

    @Test
    void modifyAccount() throws Exception {
        AccountDTO actual = new AccountDTO();
        actual.setEmail("happyday@gmail.com");

        mockMvc.perform(patch("/account-api/accounts/{id}", 1L)
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("loginId", equalTo("hwa")));
    }

    @Test
    void modifyActiveStatus() throws Exception {
        AccountDTO actual = new AccountDTO();

        mockMvc.perform(patch("/account-api/accounts/{id}/active", 1L)
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", equalTo("ACTIVE")));

    }

    @Test
    void modifyInactiveStatus() throws Exception {
        AccountDTO actual = new AccountDTO();
        mockMvc.perform(patch("/account-api/accounts/{id}/inactive", 3L)
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", equalTo("INACTIVE")));

    }

    @Test
    void withdrawAccount() throws Exception {
        mockMvc.perform(delete("/account-api/accounts/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", equalTo("WITHDRAW")));
    }
}