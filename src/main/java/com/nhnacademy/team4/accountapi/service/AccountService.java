package com.nhnacademy.team4.accountapi.service;

import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.AccountRegisterDTO;
import com.nhnacademy.team4.accountapi.dto.LoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    AccountDTO findByAccountId(Long accountId);

    AccountDTO modifyAccount(AccountRegisterDTO account, Long accountId);

    AccountDTO modifyStatus(Long accountId, AccountStatus status);

    AccountRegisterDTO register(AccountRegisterDTO accountRegisterDTO);
    List<Account> findAllAccounts();
    LoginDTO findByLoginId(Long accountId);
}
