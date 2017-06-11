package com.lorne.core.framework.exception;

/**
 * Created by yuliang on 2017/4/7.
 */
public class IOException extends LEException {

    public IOException() {
    }

    public IOException(String message) {
        super(message);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOException(Throwable cause) {
        super(cause);
    }

    public IOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
