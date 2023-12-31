package com.ssoystory.guestbookservice.exception;

public class CannotFindGuestbookException extends RuntimeException{
    public CannotFindGuestbookException(String message) {
        super(message);
    }
}
