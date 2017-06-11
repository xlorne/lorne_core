package com.lorne.core.framework.exception;

/**
 * Created by yuliang on 2017/4/18.
 */
public class ParamException extends ServiceException {

    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(int code, String message) {
        super(code, message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(int code, Throwable cause) {
        super(code, cause);
    }
}
