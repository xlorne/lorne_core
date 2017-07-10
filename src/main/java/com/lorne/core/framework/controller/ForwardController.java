package com.lorne.core.framework.controller;


import com.alibaba.fastjson.JSONObject;
import com.lorne.core.framework.Constant;
//import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuliang on 2015/8/8.
 */
@Controller
@RequestMapping("/mobile")
public class ForwardController extends AbsBaseController{


    @RequestMapping("")
    public void content(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = (String) request.getAttribute("json");
            if (StringUtils.isNotBlank(json)) {
                JSONObject jsonObject = JSONObject.parseObject(json);
                String action = jsonObject.getString("action");
                if (jsonObject.containsKey("params")) {
                    String params = jsonObject.getString("params");
                    request.setAttribute("params", params);
                }
                forward(request, response, action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void forward(HttpServletRequest request,
                        HttpServletResponse response, String path) {
        try {
            if (StringUtils.isNotEmpty(path) && !"/".equals(path)) {
                request.setAttribute(Constant.forwardKey, Constant.forwardValue);
                request.getRequestDispatcher("/mobile/" + path).forward(
                        request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
