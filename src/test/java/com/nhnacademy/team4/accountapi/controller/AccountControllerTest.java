package com.nhnacademy.team4.accountapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.team4.accountapi.domain.Account;
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
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAccounts() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].loginId",equalTo("hwa")));

    }

    @Test
    void getAccount() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("loginId", equalTo("hwa")));
    }

    @Test
    void getLogin() throws Exception{
        mockMvc.perform(get("/accounts/login/{id}","hwa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("accountId", equalTo(1)));
    }

    @Test
    void createAccount() throws Exception{
        AccountRegisterDTO actual = new AccountRegisterDTO();
        actual.setLoginId("kim");
        actual.setPassword("password");
        actual.setEmail("nhnacademy@naver.com");

        ObjectMapper objectMapper = new ObjectMapper();

//        given(accountService.register(actual)).willReturn(actual);

        mockMvc.perform(post("/accounts")
//                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actual))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loginId", equalTo("kim")));
    }

    @Test
    void modifyAccount() {
//        mockMvc.perform()
    }

    @Test
    void modifyActiveStatus() {

    }

    @Test
    void modifyInactiveStatus() {
    }

    @Test
    void withdrawAccount() {
    }
}