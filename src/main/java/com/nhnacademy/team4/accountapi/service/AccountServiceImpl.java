package com.nhnacademy.team4.accountapi.service;

import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.domain.Role;
import com.nhnacademy.team4.accountapi.dto.*;
import com.nhnacademy.team4.accountapi.exception.EmailNotExistException;
import com.nhnacademy.team4.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional //
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public AccountDTO findByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow((RuntimeException::new));
        AccountDTO accountDTO = new AccountDTO(account.getEmail(), account.getLoginId(), account.getPassword(), account.getStatus().name(), account.getRole());
        return accountDTO;
    }

    @Override
    public AccountDTO modifyAccount(AccountRegisterDTO account, Long accountId){
        Account data = accountRepository.findById(accountId).get();
        if(account.getEmail() != null){
            data.setEmail(account.getEmail());
        }
        if(account.getLoginId() != null){
            data.setLoginId(account.getLoginId());
        }
        if(account.getPassword() != null){
            data.setPassword(account.getPassword());
        }
        accountRepository.save(data);
        AccountDTO accountDTO = new AccountDTO(data.getEmail(), data.getLoginId(), data.getPassword(), data.getStatus().name(), data.getRole());

        return accountDTO;

    }

    @Override
    public AccountDTO modifyStatus(Long accountId, AccountStatus status){
        Account data = accountRepository.findById(accountId).get();
        data.setStatus(status);
        AccountDTO accountDTO = new AccountDTO(data.getEmail(), data.getLoginId(), data.getPassword(), data.getStatus().name(), data.getRole());
        return accountDTO;
    }

    @Override
    public AccountRegisterDTO register(AccountRegisterDTO accountRegisterDTO) {
        Account account = Account.builder()
                .email(accountRegisterDTO.getEmail())
                .loginId(accountRegisterDTO.getLoginId())
                .password(accountRegisterDTO.getPassword())
                .createDate(LocalDate.now())
                .status(AccountStatus.ACTIVE)
                //
                .lastLoginDate(LocalDate.now())
                //
                .role(String.valueOf(Role.USER))
                .build();
        accountRepository.save(account);
        return accountRegisterDTO;
    }
    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public LoginDTO findByLoginId(String loginId) {
        Account account = accountRepository.findByLoginId(loginId);
        LoginDTO loginDTO = new LoginDTO(account.getLoginId(), account.getPassword(), account.getStatus().name(), account.getRole());
        return loginDTO;
    }

    @Override
    public AccountIdDTO findAccountByLoginId(String loginId) {
        Account account = accountRepository.findByLoginId(loginId);
        AccountIdDTO accountIdDTO = new AccountIdDTO(account.getAccountId());
        return accountIdDTO;
    }

    @Override
    public EmailLoginDTO findAccountByEmail(String email){
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(()-> new EmailNotExistException());
        EmailLoginDTO emailLoginDTO = new EmailLoginDTO(account.getLoginId(), account.getStatus().name(), account.getRole());
        return emailLoginDTO;
    }

    @Override
    public List<Account> findProjectAccounts(List<Long> accountIds){
        return accountRepository.findByAccountIdIn(accountIds);
    }


}
