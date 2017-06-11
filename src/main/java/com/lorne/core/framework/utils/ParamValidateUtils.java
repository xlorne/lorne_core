package com.lorne.core.framework.utils;

import com.lorne.core.framework.Code;
import com.lorne.core.framework.exception.ParamException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yuliang on 2017/4/18.
 */
public class ParamValidateUtils {

    public static void isEmpty(String str) throws ParamException {
        if (StringUtils.isEmpty(str)) {
            throw new ParamException(Code.code_params_error, "参数校验异常");
        }
    }

    public static void isNotEmpty(String str) throws ParamException {
        if (StringUtils.isNotEmpty(str)) {
            throw new ParamException(Code.code_params_error, "参数校验异常");
        }
    }

}
