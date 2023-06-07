package com.nhnacademy.team4.accountapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Valid
@ToString
public class AccountRegisterDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;


}
