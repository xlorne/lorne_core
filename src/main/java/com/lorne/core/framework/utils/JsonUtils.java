package com.lorne.core.framework.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by yuliang on 2017/4/18.
 */
public class JsonUtils {

    public static JSONObject getJSONObject(String json) {
        return JSONObject.parseObject(json);
    }


    public static int getInt(String json, String key, int defaultVal) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.containsKey(key)) {
                return jsonObject.getInteger(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Long getLong(String json, String key,
                           Long defaultVal) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.containsKey(key)) {
                return jsonObject.getLong(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }


    public static double getDouble(String json, String key,
                               double defaultVal) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.containsKey(key)) {
                return jsonObject.getDouble(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static String getString(String json, String key,
                               String defaultVal) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.containsKey(key)) {
                return jsonObject.getString(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Integer getInteger(String json, String key,
                                 Integer defaultVal) {
        return getInt(json,key,defaultVal);
    }

}
