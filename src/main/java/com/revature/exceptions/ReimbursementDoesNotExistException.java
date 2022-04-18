package com.revature.exceptions;

public class ReimbursementDoesNotExistException extends RuntimeException  {
    public ReimbursementDoesNotExistException() {
        super();
    }
    public ReimbursementDoesNotExistException(String message) {
        super(message);
    }

    public ReimbursementDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReimbursementDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public ReimbursementDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
