package com.Maktab.Final.model.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value = {LogicErrorException.class})
    public ResponseEntity<Object> handelLogicException(LogicErrorException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        LogicExceptions logicErrorException = new LogicExceptions(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        logger.info(e.getMessage());
        return new ResponseEntity<>(logicErrorException, badRequest);
    }
}
