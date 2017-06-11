package com.lorne.core.framework.exception;

/**
 * Created by yuliang on 2017/4/7.
 */
public class LEException extends Exception {

    public LEException() {
    }

    public LEException(String message) {
        super(message);
    }

    public LEException(String message, Throwable cause) {
        super(message, cause);
    }

    public LEException(Throwable cause) {
        super(cause);
    }

    public LEException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
