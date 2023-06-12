package com.nhnacademy.team4.accountapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.AccountRegisterDTO;
import com.nhnacademy.team4.accountapi.repository.AccountRepository;
import com.nhnacademy.team4.accountapi.service.AccountService;
import com.nhnacademy.team4.accountapi.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.print.attribute.standard.Media;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @Order(2)
    void getAccounts() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].loginId",equalTo("hwa")));

    }

    @Test
    @Order(3)
    void getAccount() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("loginId", equalTo("hwa")));
    }

    @Test
    @Order(4)
    void getLogin() throws Exception{
        mockMvc.perform(get("/accounts/login/{id}","hwa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("accountId", equalTo(1)));
    }

    @Test
    @Order(1)
    void createAccount() throws Exception{
        AccountRegisterDTO actual = new AccountRegisterDTO();
        actual.setLoginId("kim");
        actual.setPassword("password");
        actual.setEmail("nhnacademy@naver.com");

        mockMvc.perform(post("/accounts")
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

        mockMvc.perform(patch("/accounts/{id}", 1L)
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("loginId", equalTo("hwa")));
    }

    @Test
    void modifyActiveStatus() throws Exception {
        AccountDTO actual = new AccountDTO();

        mockMvc.perform(patch("/accounts/{id}/active", 3L)
                .content(objectMapper.writeValueAsString(actual))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", equalTo("ACTIVE")));

    }

    @Test
    void modifyInactiveStatus() throws Exception{
        AccountDTO actual = new AccountDTO();
        mockMvc.perform(patch("/accounts/{id}/inactive",3L)
                .content(objectMapper.writeValueAsString(actual))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", equalTo("INACTIVE")));

    }

    @Test
    void withdrawAccount() throws Exception{
        mockMvc.perform(delete("/accounts/{id}", 3L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status",equalTo("WITHDRAW")));
    }
}