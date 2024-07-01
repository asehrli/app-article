package com.company.apparticle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyConflictException extends MyException{
    public MyConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
