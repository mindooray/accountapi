package com.nhnacademy.team4.accountapi.dto;

import com.nhnacademy.team4.accountapi.domain.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String loginId;
    private String password;
    private AccountStatus status;
    private String role;
}
