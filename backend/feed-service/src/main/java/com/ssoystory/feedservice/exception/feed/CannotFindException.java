package com.ssoystory.feedservice.exception.feed;

public class CannotFindException extends RuntimeException{
    public CannotFindException(String message) {
        super(message);
    }
}
