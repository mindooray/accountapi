package com.nhnacademy.team4.accountapi.domain;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    USER("USER");
    private String role;
    Role(String role){
        this.role=role;
    }
}
