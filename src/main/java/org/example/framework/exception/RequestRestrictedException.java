package org.example.framework.exception;

import org.example.enumaration.LogLevel;
import org.springframework.http.HttpStatus;

public class RequestRestrictedException extends GeneralRequestException {
    public RequestRestrictedException(String message, Object data) {
        super(message, data, HttpStatus.FORBIDDEN, LogLevel.DEBUG);
    }
}
