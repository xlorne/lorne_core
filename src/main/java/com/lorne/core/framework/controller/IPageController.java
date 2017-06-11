package com.lorne.core.framework.controller;




import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuliang on 2015/4/10.
 */
public interface IPageController<T> {

    Page<T> initPage(HttpServletRequest request, HttpServletResponse response)throws ServiceException;
}
