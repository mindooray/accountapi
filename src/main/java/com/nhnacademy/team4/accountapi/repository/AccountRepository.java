package com.nhnacademy.team4.accountapi.repository;

import com.nhnacademy.team4.accountapi.domain.Account;
import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import com.nhnacademy.team4.accountapi.dto.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByLoginId (String loginId);
}
