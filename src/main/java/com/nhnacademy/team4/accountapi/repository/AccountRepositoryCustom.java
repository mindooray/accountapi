package com.nhnacademy.team4.accountapi.repository;

import com.nhnacademy.team4.accountapi.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountRepositoryCustom {
    Page<AccountDTO> findAllByPage(Long currentAccont, Pageable pageable);
}
