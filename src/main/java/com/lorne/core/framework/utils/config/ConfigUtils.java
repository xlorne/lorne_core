package com.lorne.core.framework.utils.config;

/**
 * Created by yuliang on 2017/4/7.
 */
public class ConfigUtils {

    public static String getString(String filePath, String key) {
        ConfigHelper helper = new ConfigHelper(filePath);
        return helper.getStringValue(key);
    }

    public static int getInt(String filePath, String key) {
        ConfigHelper helper = new ConfigHelper(filePath);
        return helper.getIntValue(key);
    }

    public static void setProperty(String filePath, String key, Object val) {
        ConfigHelper helper = new ConfigHelper(filePath);
        helper.setProperty(key, val);
    }


    public static String[] getStringArrayValue(String filePath, String key) {
        ConfigHelper helper = new ConfigHelper(filePath);
        return helper.getStringArrayValue(key);
    }

}
