package com.lorne.core.framework.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.lorne.core.framework.controller.AbsBaseController;
import com.lorne.core.framework.model.Msg;
import com.lorne.core.framework.model.Response;
//import net.sf.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuliang on 2016/12/16.
 */
public abstract class AbsBaseInterceptor extends HandlerInterceptorAdapter {


    protected void outJson(HttpServletResponse response, String msg, int code) throws Exception {
        Response res = new Response();
        res.setCode(code);
        res.setMsg(msg);
        Msg _msg = new Msg(AbsBaseController.state_success ? 1 : 0, res);
        String json =  JSONObject.toJSONString(_msg);
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Content-type",
                "application/json;charset=utf-8");
        response.getWriter().print(json);
    }
}
