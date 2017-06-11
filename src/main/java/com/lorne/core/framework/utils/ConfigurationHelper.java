package com.lorne.core.framework.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by yuliang on 2015/8/3.
 */
public class ConfigurationHelper {


    private PropertiesConfiguration propertiesConfiguration = null;

    public String getStringValue(String key) {
        return propertiesConfiguration.getString(key);
    }

    public void setProperty(String key, Object val) {
        propertiesConfiguration.setProperty(key, val);
        try {
            propertiesConfiguration.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public int getIntValue(String key) {
        return propertiesConfiguration.getInt(key);
    }

    public float getFloatValue(String key) {
        return propertiesConfiguration.getFloat(key);
    }

    public ConfigurationHelper(String propertyPath) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource(propertyPath).getPath();
            propertiesConfiguration = new PropertiesConfiguration(path);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Please configure the serverurl in the file " + propertyPath);
        }
    }

}
