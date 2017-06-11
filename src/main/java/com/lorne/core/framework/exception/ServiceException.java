package com.lorne.core.framework.exception;

import com.lorne.core.framework.Code;

/**
 * Created by yuliang on 2017/4/7.
 */
public class ServiceException extends LEException {


    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private void init(){
        code = Code.code_error;
    }

    public ServiceException() {
        init();
    }

    public ServiceException(String message) {
        super(message);
        init();
    }

    public ServiceException(int code,String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        init();
    }

    public ServiceException(Throwable cause) {
        super(cause);
        init();
    }

    public ServiceException(int code,Throwable cause) {
        super(cause);
        this.code = code;
    }

}
