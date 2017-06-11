package com.lorne.core.framework;

public interface Code {

    /**
     * 执行成功
     */
    int code_success = 40000;

    /**
     * 执行失败
     */
    int code_error = 40010;

    /**
     * 没有授权
     */
    int code_no_authorization = 30000;


    /**
     * 请求方式不正确
     */
    int code_method_is_not_post = 31000;


    /**
     * 传入参数不符合接口要求
     */
    int code_params_error = 40100;

    /**
     * 数据库执行异常
     */
    int code_db_error = 40200;




}
