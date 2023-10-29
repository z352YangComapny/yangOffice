package com.ssoystory.feedservice.exception;

public class UnAuthorizedError extends RuntimeException{
    public UnAuthorizedError(String message) {
        super(message);
    }
}