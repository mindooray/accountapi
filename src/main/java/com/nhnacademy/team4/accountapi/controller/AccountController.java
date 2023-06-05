package com.nhnacademy.team4.accountapi.controller;


import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.AccountRegisterDTO;
import com.nhnacademy.team4.accountapi.dto.LoginDTO;
import com.nhnacademy.team4.accountapi.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    AccountStatus active = AccountStatus.ACTIVE;
    AccountStatus inActive = AccountStatus.INACTIVE;
    AccountStatus withdraw = AccountStatus.WITHDRAW;
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.findAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id")Long accountId){
        AccountDTO account = accountService.findByAccountId(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<LoginDTO> getLogin(@PathVariable("id")Long accountId){
        LoginDTO login = accountService.findByLoginId(accountId);
        return ResponseEntity.ok(login);
    }

    //회원가입
    @PostMapping
    public ResponseEntity<AccountRegisterDTO> createAccount(@Validated @RequestBody AccountRegisterDTO accountRegisterDTO){
        AccountRegisterDTO account = accountService.register(accountRegisterDTO);
        return ResponseEntity.ok(account);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AccountDTO> modifyAccount(@PathVariable("id")Long accountId, @RequestBody AccountRegisterDTO accountRegisterDTO){
        AccountDTO modifyAccount = accountService.modifyAccount(accountRegisterDTO ,accountId);
        return ResponseEntity.ok(modifyAccount);

    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<AccountDTO> modifyActiveStatus(@PathVariable("id")Long accountId){
        AccountDTO modifyAccountStatus = accountService.modifyStatus(accountId, active);
        return ResponseEntity.ok(modifyAccountStatus);
    }


    @PatchMapping("/{id}/inactive")
    public ResponseEntity<AccountDTO> modifyInactiveStatus(@PathVariable("id")Long accountId){
        AccountDTO modifyAccountStatus = accountService.modifyStatus(accountId, inActive);
        return ResponseEntity.ok(modifyAccountStatus);
    }

    //
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> withdrawAccount(@PathVariable("id")Long accountId) {
        AccountDTO modifyAccountStatus = accountService.modifyStatus(accountId, withdraw);
        return ResponseEntity.ok(modifyAccountStatus);
    }




}
