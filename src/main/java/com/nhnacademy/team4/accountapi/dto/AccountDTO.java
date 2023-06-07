package com.nhnacademy.team4.accountapi.dto;

import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import com.nhnacademy.team4.accountapi.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String email;
    private String loginId;
    private String password;
    private String status;
    private String role;

}
