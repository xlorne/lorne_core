package com.lorne.core.framework.model;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author yuliang
 * @version 1.0
 */
public class JsonModel extends MapModel{

    public String toJsonString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }


}
