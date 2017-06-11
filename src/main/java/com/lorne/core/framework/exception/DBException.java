package com.lorne.core.framework.exception;

/**
 * Created by yuliang on 2017/4/7.
 */
public class DBException extends ServiceException {

    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }


}
