package com.company.apparticle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyNotFoundException extends MyException{
    public MyNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
