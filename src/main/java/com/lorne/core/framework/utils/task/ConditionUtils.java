package com.lorne.core.framework.utils.task;


import com.lorne.core.framework.utils.config.ConfigUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yuliang on 2016/4/28.
 */
public class ConditionUtils {

    private static ConditionUtils instance = null;

    private Map<String, Task> taskMap = new ConcurrentHashMap<String, Task>();

    private List<Task> pools = new CopyOnWriteArrayList<>();

    private final int init_pool_size = 10;

    private int max_pool_size = 100;

    private ConditionUtils(){

        try {
            max_pool_size = ConfigUtils.getInt("task.properties","max.pool.size");
        }catch (Exception e){
            max_pool_size = 100;
        }
        String str = String.format(" this max pool size is %d",max_pool_size);
        System.out.println(str);

        for(int i=0;i<init_pool_size;i++){
            create();
        }
    }

    private Task create(){
        int size = pools.size();
        if(size>=max_pool_size){
            String str = String.format(" this max pool size is %d,but now size is %d .",max_pool_size,size);
            throw new RuntimeException(str);
        }
        Task task =  new Task();
        task.setIsUsed(0);
        pools.add(task);
        return task;
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

    private synchronized Task loadTask(){
        int size = pools.size();
        for(int i=0;i<size;i++){
            Task task = pools.get(i);
            if(task.getIsUsed()==0){
                return task;
            }
        }
        return create();
    }


    public Task createTask(String key) {
        Task task = loadTask();
        task.setKey(key);
        task.setIsUsed(1);
        taskMap.put(key, task);
        return task;
    }


    public  Task getTask(String key) {
        return taskMap.get(key);
    }


    public void removeKey(String key) {
        if (StringUtils.isNotEmpty(key)) {
            taskMap.remove(key);
        }
    }

    public boolean hasKey(String key) {
        return taskMap.containsKey(key);
    }
}
