package com.att.tdp.bisbis10.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception class for handling bad requests
 * It extends the RuntimeException class
 * When this exception is thrown, it returns a HTTP status of BAD_REQUEST (400)
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Constructor for BadRequestException
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public BadRequestException(String message) {
        super(message);
    }
}