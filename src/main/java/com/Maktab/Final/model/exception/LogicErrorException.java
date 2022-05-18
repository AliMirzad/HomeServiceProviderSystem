package com.Maktab.Final.model.exception;


import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class LogicErrorException extends RuntimeException {
    public LogicErrorException(String message) {
        super(message);
    }

    public LogicErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
