package com.nhnacademy.team4.accountapi.advice;


import com.nhnacademy.team4.accountapi.exception.EmailNotExistException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.Email;

//@RestControllerAdvice
//public class CommonAdvice extends ResponseEntityExceptionHandler {
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(EmailNotExistException.class)
//    public String notFoundAccountException(EmailNotExistException e){
//        logger.error("EmailNotExist:{}");
//        return e.getMessage();
//    }
//
//
//}
