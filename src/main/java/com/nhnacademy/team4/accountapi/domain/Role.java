package com.nhnacademy.team4.accountapi.domain;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private String role;
    Role(String role){
        this.role=role;
    }
}
