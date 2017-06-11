package com.lorne.core.framework.controller;

import com.lorne.core.framework.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yuliang on 2016/7/1.
 */
public interface ITokenService {

    Object init(HttpServletRequest request, HttpServletResponse response, Map<String, Object> sessionUser, String json) throws ServiceException;
}
