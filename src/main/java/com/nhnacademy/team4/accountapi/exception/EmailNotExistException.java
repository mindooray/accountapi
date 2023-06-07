package com.nhnacademy.team4.accountapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailNotExistException extends RuntimeException{
    public EmailNotExistException() { super("회원 이메일이 존재하지 않습니다.");}
}
