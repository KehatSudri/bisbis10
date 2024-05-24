package com.att.tdp.bisbis10.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a utility class that provides common functionality across the application
 * It is final and cannot be instantiated
 */
public final class Utils {

    /**
     * Private constructor to prevent instantiation
     * Throws an AssertionError if an attempt is made
     */
    private Utils() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Builds a standardized error response for exceptions
     * @param e the exception
     * @param status the HTTP status
     * @param request the web request that resulted in an exception
     * @return a response entity with the exception details and HTTP status
     */
    public static ResponseEntity<Object> buildErrorResponse(Exception e, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, status);
    }

}