package com.lorne.core.framework.interceptor;


import com.lorne.core.framework.Code;
import com.lorne.core.framework.utils.encode.MD5Util;
import com.lorne.core.framework.utils.TokenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * rest api 接口
 * Created by Dylan on 2016/6/29.
 */
public abstract class BaseMobileRESTAPIInterceptorAdapter extends AbsBaseInterceptor {

    public abstract void loadVerificationPath(List<String> verificationPath);

    /**
     * 不需要链接的路径地址
     */
    private List<String> verificationPath = null;

    public BaseMobileRESTAPIInterceptorAdapter() {
        super();
        verificationPath = new ArrayList<String>();

        loadVerificationPath(verificationPath);
    }


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path == null) {
            return false;
        }
        if (path.equals("/")) {
            return true;
        }
        if (path.contains("/mobile")) {
            if (!"POST".equals(request.getMethod())) {
                outJson(response, "http发起请求方式不对.", Code.code_method_is_not_post);
                return false;
            }
            // 手机接口请求
            try {
                String json = IOUtils.toString(request.getInputStream(), "utf-8");
                String sign = ServletRequestUtils.getStringParameter(request, "sign", "");
                String token = ServletRequestUtils.getStringParameter(request, "token", "");
                String key = null;
                if (token == null || token.equals("")) {
                    key = MD5Util.md5("content=" + json);
                } else {
                    key = MD5Util.md5("token=" + token + "&content=" + json);
                }
                if (!sign.equals(key)) {
                    outJson(response, "请核对参数后再试", Code.code_method_is_not_post);
                    return false;
                }
                request.setAttribute("json", json);
                request.setAttribute("sign", sign);
                if (verificationPath.contains(path)) {
                    return true;
                }

                Map<String, Object> sessionUser = TokenUtils.getUserByKey(token);
                if (sessionUser == null) {
                    outJson(response, "该用户没有授权", Code.code_no_authorization);
                    return false;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
