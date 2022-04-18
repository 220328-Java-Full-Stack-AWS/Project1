package com.revature.exceptions;

public class UnvalidPermissionException extends RuntimeException {
    public UnvalidPermissionException() {
        super();
    }
    public UnvalidPermissionException(String message) {
        super(message);
    }

    public UnvalidPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnvalidPermissionException(Throwable cause) {
        super(cause);
    }

    public UnvalidPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
