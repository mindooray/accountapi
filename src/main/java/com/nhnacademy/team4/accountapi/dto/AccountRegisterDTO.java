package com.nhnacademy.team4.accountapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class AccountRegisterDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;


}
