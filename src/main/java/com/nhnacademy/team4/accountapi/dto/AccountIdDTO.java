package com.nhnacademy.team4.accountapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountIdDTO {

    private Long accountId;
    private String loginId;
    private String password;
    private String Role;
    private String Status;

}

