package com.nhnacademy.team4.accountapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ExistLoginException extends RuntimeException{
    public ExistLoginException() { super("회원ID가 중복입니다.");}

}
