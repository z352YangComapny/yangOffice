package com.ssoystory.memberservice.exception;

public class UnAuthorizedError extends RuntimeException{
    public UnAuthorizedError(String message) {
        super(message);
    }
}