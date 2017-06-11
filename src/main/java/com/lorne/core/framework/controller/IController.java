package com.lorne.core.framework.controller;


import com.lorne.core.framework.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {

	Object initData(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
