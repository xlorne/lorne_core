package com.lorne.core.framework.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuliang on 2016/12/16.
 */
public abstract class AbsBaseController {


    protected boolean isDebug = true;
    protected static final String paramsKey = "params";

    protected int nowPage;
    protected int pageSize;
    protected String key;
    protected String order;

    protected static final String dbError = "执行数据库出现异常,请稍后再试.";


    private static final String utf8 = "UTF-8";
    public static final boolean state_success = true;
    public static final boolean state_error = false;

    protected void setUTF8(Object... objs) throws Exception {
        if (objs != null && objs.length > 0) {
            for (Object o : objs) {
                if (o instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) o;
                    request.setCharacterEncoding(utf8);
                }
//                if (o instanceof HttpServletResponse) {
//                    HttpServletResponse response = (HttpServletResponse) o;
//                    response.setCharacterEncoding(utf8);
//                }
            }
        }
    }

    protected void out2html(HttpServletResponse response, String json) {
        try {

            response.setContentType("text/html;charset=utf-8");
            response
                    .setHeader("Content-type", "application/json;charset=utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
