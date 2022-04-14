package com.revature.exceptions;

public class LoginUnsuccessfulException extends RuntimeException{
    public LoginUnsuccessfulException() {
        super();
    }

    public LoginUnsuccessfulException(String message) {
        super(message);
    }

    public LoginUnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginUnsuccessfulException(Throwable cause) {
        super(cause);
    }

    public LoginUnsuccessfulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
