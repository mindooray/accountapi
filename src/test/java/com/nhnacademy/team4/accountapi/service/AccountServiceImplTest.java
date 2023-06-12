package com.nhnacademy.team4.accountapi.service;

import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.AccountIdDTO;
import com.nhnacademy.team4.accountapi.dto.AccountRegisterDTO;
import com.nhnacademy.team4.accountapi.dto.EmailLoginDTO;
import com.nhnacademy.team4.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

//@SpringBootTest

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    void findByAccountId() {
        // given
        Account virtual = new Account(33L,"1123124", "hello", "1234", LocalDate.now(), AccountStatus.ACTIVE,LocalDate.now(),"USER" );
//        accountRepository.save(virtual);

        // when
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(virtual));
//                .thenReturn(Optional.empty());

        // then
        accountService.findByAccountId(anyLong());

        verify(accountRepository, times(1))
                .findById(anyLong());
    }

    @Test
    void modifyAccount() {
        AccountRegisterDTO accountRegisterDTO = new AccountRegisterDTO();
        accountRegisterDTO.setEmail("nhn@example.com");
        accountRegisterDTO.setLoginId("nhnId");
        accountRegisterDTO.setPassword("1234");

        Account existAccount = new Account(22L, "oldemail@example.com","oldLogin","oldPwd",LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(),"USER");

        when(accountRepository.findById(22L)).thenReturn(Optional.of(existAccount));
        when(accountRepository.save(existAccount)).thenReturn(existAccount);

        AccountDTO modified = accountService.modifyAccount(accountRegisterDTO, 22L);

        verify(accountRepository).findById(22L);
        verify(accountRepository).save(existAccount);

        assertThat(modified.getEmail()).isEqualTo(accountRegisterDTO.getEmail());
        assertThat(modified.getLoginId()).isEqualTo(accountRegisterDTO.getLoginId());
        assertThat(modified.getPassword()).isEqualTo(accountRegisterDTO.getPassword());
        assertThat(modified.getStatus()).isEqualTo(existAccount.getStatus().name());
        assertThat(modified.getRole()).isEqualTo(existAccount.getRole());
    }

    @Test
    void modifyStatus() {
        Account existAccount = new Account(22L, "oldemail@example.com","oldLogin","oldPwd",LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(),"USER");

        when(accountRepository.findById(22L)).thenReturn(Optional.of(existAccount));

        AccountDTO modified = accountService.modifyStatus(22L, AccountStatus.INACTIVE);

        verify(accountRepository).findById(22L);
//        verify(accountService).modifyStatus(22L, AccountStatus.INACTIVE); 이거 mock이 아니라서 안된다네?
        assertThat(modified.getStatus()).isEqualTo("INACTIVE");
    }

    @Test
    void register() {
        AccountRegisterDTO accountRegisterDTO = new AccountRegisterDTO();
        accountRegisterDTO.setEmail("nhn@example.com");
        accountRegisterDTO.setLoginId("nhn");
        accountRegisterDTO.setPassword("1234");

        accountService.register(accountRegisterDTO);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void findAllAccounts() {
        Account account1 = new Account(1L, "test1@example.com", "testlogin1", "testpassword1", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        Account account2 = new Account(2L, "test2@example.com", "testlogin2", "testpassword2", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        List<Account> expectedAccounts = Arrays.asList(account1, account2);

        // Mock repository behavior
        when(accountRepository.findAll()).thenReturn(expectedAccounts);

        // When
        List<Account> actualAccounts = accountService.findAllAccounts();

        // Then
        assertThat(actualAccounts).isEqualTo(expectedAccounts);
    }

    @Test
    void findByLoginId() {

        Account account = new Account(22L, "oldemail@example.com","oldLogin","oldPwd",LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(),"USER");
        when(accountRepository.findByLoginId(anyString()))
                .thenReturn(Optional.of(account).get());

        // then
        accountService.findByLoginId(anyString());

        verify(accountRepository, times(1))
                .findByLoginId(anyString());
    }

    @Test
    void findAccountByLoginId() {
        String loginId = "loginId";
        Long accountId = 33L;
        Account account =  new Account(accountId, "test@example.com", loginId, "testpassword", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        when(accountRepository.findByLoginId(loginId)).thenReturn(account);

        // When
        AccountIdDTO accountIdDTO = accountService.findAccountByLoginId(loginId);

        // Then
        assertThat(accountIdDTO.getAccountId()).isEqualTo(accountId);

    }

    @Test
    void findAccountByEmail() {
        String email = "test@example.com";
        String loginId = "testlogin";
        Account account = new Account(33L, email, loginId, "testpassword", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");

        // Mock repository behavior
        when(accountRepository.findAccountByEmail(email)).thenReturn(Optional.of(account));

        // When
        EmailLoginDTO emailLoginDTO = accountService.findAccountByEmail(email);

        // Then
        assertThat(emailLoginDTO.getLoginId()).isEqualTo(loginId);
        assertThat(emailLoginDTO.getStatus()).isEqualTo(account.getStatus().name());
        assertThat(emailLoginDTO.getRole()).isEqualTo(account.getRole());
    }

    @Test
    void findProjectAccounts(){
        List<Long> accountIds = Arrays.asList(1L, 2L, 3L);
        Account account1 = new Account(1L, "test1@example.com", "testlogin1", "testpassword1", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        Account account2 = new Account(2L, "test2@example.com", "testlogin2", "testpassword2", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        List<Account> expectedAccounts = Arrays.asList(account1, account2);

        // Mock repository behavior
        when(accountRepository.findByAccountIdIn(accountIds)).thenReturn(expectedAccounts);

        // When
        List<Account> actualAccounts = accountService.findProjectAccounts(accountIds);

        // Then
        assertThat(actualAccounts).isEqualTo(expectedAccounts);
    }
}