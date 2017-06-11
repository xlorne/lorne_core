package com.lorne.core.framework.utils;


import com.lorne.core.framework.model.ValueFilter;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author yuliang
 * @version 1.0
 * @date 2015-3-16 下午08:44:38
 */
public class JsonFormatUtils {

    private static final String FILTERVALUE = "FILTERVALUE";
    private static final String FILTERTYPE = "FILTERTYPE";

    public static String toJsonString(Object obj, ValueFilter... vfs) {
        return toJsonString(obj, createFilterList(vfs), vfs);
    }

    private static String toJsonString(Object obj,
                                       final List<Map<String, Object>> fileds, final ValueFilter... vfs) {
        JsonDateValueProcessor jsonDateValueProcessor = new JsonDateValueProcessor();
        String jsonArray = null;
        if (fileds != null && fileds.size() > 0) {

            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object source, String name, Object value) {
                    for (Map<String, Object> m : fileds) {
                        final Class type = (Class) m.get(FILTERTYPE);
                        final String values[] = (String[]) m.get(FILTERVALUE);
                        if (values != null)
                            for (String str : values) {
                                if (str.equals(name)) {
                                    Class mtype = source.getClass();
                                    if (mtype.equals(type))
                                        return true;
                                }
                            }
                    }
                    return false;
                }
            });
            jsonConfig
                    .setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            jsonConfig.registerJsonValueProcessor(Date.class, jsonDateValueProcessor);
            jsonConfig.registerJsonValueProcessor(java.sql.Date.class, jsonDateValueProcessor);
            jsonConfig.registerJsonValueProcessor(Timestamp.class, jsonDateValueProcessor);
            valueFilter(jsonConfig, vfs);
            JSONObject iob = JSONObject.fromObject(obj, jsonConfig);
            jsonArray = iob.toString();
        } else {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object source, String name, Object value) {
                    return false;
                }
            });
            jsonConfig
                    .setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            jsonConfig.registerJsonValueProcessor(Date.class, jsonDateValueProcessor);
            jsonConfig.registerJsonValueProcessor(Timestamp.class, jsonDateValueProcessor);
            jsonConfig.registerJsonValueProcessor(java.sql.Date.class, jsonDateValueProcessor);
            valueFilter(jsonConfig, vfs);
            JSONObject iob = JSONObject.fromObject(obj, jsonConfig);
            jsonArray = iob.toString();
        }
        return jsonArray;
    }

    private static void valueFilter(JsonConfig jsonConfig, ValueFilter... vfs) {
        if (vfs != null)
            for (final ValueFilter valueFilter : vfs) {
                jsonConfig.registerJsonValueProcessor(valueFilter.getType(), new JsonValueProcessor() {
                    public Object processArrayValue(Object paramObject,
                                                    JsonConfig paramJsonConfig) {
                        return process(paramObject);
                    }

                    public Object processObjectValue(String paramString, Object paramObject,
                                                     JsonConfig paramJsonConfig) {
                        return process(paramObject);
                    }

                    private Object process(Object value) {
                        return valueFilter.getFilter().process(value);
                    }
                });
            }
    }

    /**
     * 创建json拦截
     *
     * @param vfs 拦截对象及属性 new
     *            ValueFilter(Images.class,"name","status","uploadpath")
     *            对Images对象拦截 拦截掉name,status,uploadpath属性
     * @return
     */
    private static List<Map<String, Object>> createFilterList(
            ValueFilter... vfs) {
        if (vfs == null || vfs.length <= 0)
            return null;
        List<Map<String, Object>> fileds = createList();
        for (ValueFilter vf : vfs) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put(FILTERTYPE, vf.getType());
            m.put(FILTERVALUE, vf.getValues());
            fileds.add(m);
        }
        return fileds;
    }

    private static List<Map<String, Object>> createList() {
        return new ArrayList<Map<String, Object>>();

    }
}
