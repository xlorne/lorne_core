package com.lorne.core.framework.controller;



import com.lorne.core.framework.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuliang on 2016/7/1.
 */
public interface IService {

    Object init(HttpServletRequest request, HttpServletResponse response, String json) throws ServiceException;
}
