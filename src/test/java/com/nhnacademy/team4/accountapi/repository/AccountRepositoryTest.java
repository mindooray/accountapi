package com.nhnacademy.team4.accountapi.repository;

import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByLoginId() {
        String loginId = "testlogin1";
        Account account = new Account(33L, "test1@example.com", loginId, "testpassword1", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        accountRepository.save(account);

        // When
        Account foundAccount = accountRepository.findByLoginId(loginId);

        // Then
        assertThat(foundAccount).isEqualTo(account);
    }

    @Test
    void findAccountByEmail() {
        String email ="test1@example.com";
        Account account = new Account(33L, email, "testlogin", "testpassword1", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        accountRepository.save(account);

        // When
        Optional<Account> foundAccount = accountRepository.findAccountByEmail(email);

        // Then
        assertThat(foundAccount).isEqualTo(Optional.of(account));
    }

    @Test
    void findByAccountIdIn(){
        List<Long> accountIds = Arrays.asList(1L, 2L, 3L);
        Account account1 = new Account(1L, "test1@example.com", "testlogin1", "testpassword1", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        Account account2 = new Account(2L, "test2@example.com", "testlogin2", "testpassword2", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        Account account3 = new Account(3L, "test3@example.com", "testlogin3", "testpassword3", LocalDate.now(), AccountStatus.ACTIVE, LocalDate.now(), "USER");
        accountRepository.saveAll(Arrays.asList(account1, account2, account3));

        // When
        List<Account> foundAccounts = accountRepository.findByAccountIdIn(accountIds);

        // Then
        assertThat(foundAccounts.size()).isEqualTo(3);
    }
}