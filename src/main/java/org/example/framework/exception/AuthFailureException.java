package org.example.framework.exception;

import org.example.enumaration.LogLevel;
import org.springframework.http.HttpStatus;

public class AuthFailureException extends GeneralRequestException {
    public AuthFailureException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, LogLevel.TRACE);
    }

    public AuthFailureException(String message, HttpStatus httpStatus) {
        super(message, httpStatus, LogLevel.TRACE);
    }
}
