package com.lorne.core.framework.utils.task;


import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yuliang on 2016/4/28.
 */
public class ConditionUtils {

    private Lock lock;
    private Condition condition;

    private static ConditionUtils instance = null;

    private static Map<String, Task> taskMap = new ConcurrentHashMap<String, Task>();


    private ConditionUtils(){
        lock = new ReentrantLock();
        condition = lock.newCondition();
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
        Task task = new Task(lock,condition);
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
