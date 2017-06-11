package com.lorne.core.framework.utils;

/**
 * Created by yuliang on 2017/4/7.
 */
public class ConfigurationUtils {

    public static String getString(String filePath, String key) {
        ConfigurationHelper helper = new ConfigurationHelper(filePath);
        return helper.getStringValue(key);
    }

    public static int getInt(String filePath, String key) {
        ConfigurationHelper helper = new ConfigurationHelper(filePath);
        return helper.getIntValue(key);
    }

    public static void setProperty(String filePath, String key, Object val) {
        ConfigurationHelper helper = new ConfigurationHelper(filePath);
        helper.setProperty(key, val);
    }

}
