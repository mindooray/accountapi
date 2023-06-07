package com.nhnacademy.team4.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailLoginDTO implements DTO{
    private String loginId;
    private String status;
    private String role;
}
