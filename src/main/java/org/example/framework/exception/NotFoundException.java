package org.example.framework.exception;

import lombok.Getter;
import org.example.enumaration.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends GeneralRequestException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, LogLevel.TRACE);
    }

    public NotFoundException() {
        super("", HttpStatus.NOT_FOUND, LogLevel.TRACE);
    }
}
