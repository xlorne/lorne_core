package com.lorne.core.framework.controller;


import com.lorne.core.framework.Code;
import com.lorne.core.framework.exception.DBException;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Msg;
import com.lorne.core.framework.model.Response;
import com.lorne.core.framework.utils.json.JsonUtils;
import com.lorne.core.framework.utils.TokenUtils;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yuliang on 2016/7/1.
 */
public class RestController extends AbsBaseController {



    private void encode(HttpServletRequest request, HttpServletResponse response, Object service){
        Msg m = null;
        Response res = new Response();

        try {
            String json = (String) request.getAttribute("json");


            Map<String, Object> sessionUser = null;
            if(service instanceof ITokenService){
                String token = request.getParameter("token");
                sessionUser = TokenUtils.getUserByKey(token);
            }

            setUTF8(request, response);
            m = new Msg();
            m.setState(state_success ? 1 : 0);
            try {
                res.setCode(Code.code_success);

                if(service instanceof ITokenService){
                    ITokenService controller = (ITokenService) service;
                    res.setData(controller.init(request, response, sessionUser, json));
                }else{
                    IService controller = (IService) service;
                    res.setData(controller.init(request, response, json));
                }


            } catch (ServiceException e) {
                if (e instanceof DBException) {
                    res.setCode(Code.code_db_error);
                    res.setMsg(dbError);
                } else {
                    res.setCode(e.getCode());
                    res.setMsg(e.getLocalizedMessage());
                }
            }
            m.setRes(res);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause() instanceof ServiceException) {
                ServiceException exception = (ServiceException) e.getCause();
                res.setCode(exception.getCode());
                res.setMsg(exception.getLocalizedMessage());
                m = new Msg();
                m.setState(state_success ? 1 : 0);
                m.setRes(res);
            } else {
                String emsg = e.getLocalizedMessage();
                if (isDebug)
                    e.printStackTrace();
                m = new Msg(state_error ? 1 : 0);
                m.setMsg(emsg);
            }
        }
        out2html(response, m.toJsonString());
    }

    protected void initEncode(HttpServletRequest request, HttpServletResponse response, ITokenService controller) {
        encode(request,response,controller);
    }


    protected void initEncode(HttpServletRequest request, HttpServletResponse response, IService controller) {
        encode(request,response,controller);
    }

    protected JSONObject getJSONObject(String json) {
        return JsonUtils.getJSONObject(json);
    }


    protected int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }

    protected Long getLong(String json, String key,
                           Long defaultVal) {
        return JsonUtils.getLong(json, key, defaultVal);
    }

    protected double getDouble(String json, String key,
                               double defaultVal) {
         return JsonUtils.getDouble(json, key, defaultVal);
    }

    protected String getString(String json, String key,
                               String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    protected Integer getInteger(String json, String key,
                                 Integer defaultVal) {
        return JsonUtils.getInteger(json, key, defaultVal);
    }

}
