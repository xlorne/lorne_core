package com.lorne.core.framework.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuliang on 2017/4/7.
 */
public class MapModel implements Serializable {

    public Map<String,Object> toMap(){
        Class clazz = getClass();
        Map<String, Object> maps = new HashMap<String, Object>();
        PropertyDescriptor[] propertyDescriptors = null;
        try {
            propertyDescriptors = propertyDescriptors(clazz);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method gmethod = propertyDescriptor.getReadMethod();
                Method smethod = propertyDescriptor.getWriteMethod();
                if (null != gmethod && smethod != null) {
                    try {
                        maps.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return maps;
        } catch (SQLException e) {
            return null;
        }
    }

    private PropertyDescriptor[] propertyDescriptors(Class<?> c) throws SQLException {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(c);
        } catch (IntrospectionException var4) {
            throw new SQLException("Bean introspection failed: " + var4.getMessage());
        }
        return beanInfo.getPropertyDescriptors();
    }
}
