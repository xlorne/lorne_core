package com.lorne.core.framework.utils.task;


import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuliang on 2016/4/28.
 */
public class ConditionUtils {



    private static ConditionUtils instance = null;

    private Map<String, Task> taskMap = new ConcurrentHashMap<String, Task>();


    private ConditionUtils(){

    }

    public static ConditionUtils getInstance() {
        if (instance == null) {
            synchronized (ConditionUtils.class) {
                if(instance==null){
                    instance = new ConditionUtils();
                }
            }
        }
        return instance;
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
            System.out.println("taskMap-size->"+taskMap.size());
        }
    }

    public boolean hasKey(String key) {
        return taskMap.containsKey(key);
    }
}
