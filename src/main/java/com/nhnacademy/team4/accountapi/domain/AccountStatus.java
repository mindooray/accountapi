package com.nhnacademy.team4.accountapi.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum AccountStatus {

    ACTIVE("ACTIVE"),
    WITHDRAW("WITHDRAW"),
    INACTIVE("INACTIVE");

    private String status;

    AccountStatus(String status){
        this.status = status;
    }


}
