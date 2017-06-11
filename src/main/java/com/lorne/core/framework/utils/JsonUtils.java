package com.lorne.core.framework.utils;

import net.sf.json.JSONObject;

/**
 * Created by yuliang on 2017/4/18.
 */
public class JsonUtils {

    public static JSONObject getJSONObject(String json) {
        return JSONObject.fromObject(json);
    }


    public static int getInt(String json, String key, int defaultVal) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.has(key)) {
                return jsonObject.getInt(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Long getLong(String json, String key,
                           Long defaultVal) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.has(key)) {
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
        JSONObject jsonObject = JSONObject.fromObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.has(key)) {
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
        JSONObject jsonObject = JSONObject.fromObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.has(key)) {
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
        JSONObject jsonObject = JSONObject.fromObject(json);
        try {
            if (jsonObject == null) {
                return defaultVal;
            }
            if (jsonObject.has(key)) {
                return jsonObject.getInt(key);
            } else {
                return defaultVal;
            }
        } catch (Exception e) {
            return defaultVal;
        }
    }

}
