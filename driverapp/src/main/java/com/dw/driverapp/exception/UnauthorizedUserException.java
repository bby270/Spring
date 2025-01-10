package com.dw.driverapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super();
    }
    public UnauthorizedUserException(String message) {
        super(message);
    }
}