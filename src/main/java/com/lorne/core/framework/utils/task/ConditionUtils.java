package com.lorne.core.framework.utils.task;


import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuliang on 2016/4/28.
 */
public class ConditionUtils {

    private static ConditionUtils condition = null;

    private static Map<String, Task> taskMap = new ConcurrentHashMap<String, Task>();

    static {
        condition = getInstance();
    }

    public static ConditionUtils getInstance() {
        if (condition == null) {
            condition = new ConditionUtils();
        }
        return condition;
    }



    public Task createTask(String key) {
        Task task = new Task();
        task.setKey(key);
        taskMap.put(key, task);
        return task;
    }


    public Task getTask(String key) {
        return taskMap.get(key);
    }


    public void removeKey(String key) {
        if (StringUtils.isNotEmpty(key)) {
            taskMap.remove(key);
        }
    }

}
