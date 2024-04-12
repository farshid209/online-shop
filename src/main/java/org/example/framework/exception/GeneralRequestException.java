package org.example.framework.exception;

import lombok.Getter;
import org.example.enumaration.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralRequestException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final LogLevel level;

    private Object data;

    public GeneralRequestException(String message, HttpStatus httpStatus, LogLevel level) {
        super(message);
        this.httpStatus = httpStatus;
        this.level = level;
    }

    public GeneralRequestException(String message, Object data, HttpStatus httpStatus, LogLevel level) {
        super(message);
        this.data = data;
        this.httpStatus = httpStatus;
        this.level = level;
    }
}
