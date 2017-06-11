package com.lorne.core.framework.utils.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuliang on 2016/6/27.
 */
public class CountDownLatchHelper<T> {

    private boolean isExecute = false;
    private int count;
    private List<T> data;
    private List<CountDownThread<T>> list;
    private CountDownLatch end;
    private ExecutorService threadPool = null;
    private List<IExecute<T>> executes = null;

    public CountDownLatchHelper() {
        threadPool = Executors.newFixedThreadPool(10);
        executes = new ArrayList<IExecute<T>>();
        data = Collections.synchronizedList(new ArrayList<T>());
        list = new ArrayList<CountDownThread<T>>();
    }

    public CountDownLatchHelper<T> addExecute(IExecute<T> execute) {
        executes.add(execute);
        return this;
    }

    public CountDownLatchHelper<T> execute() {
        this.count = executes.size();
        if (this.count > 0) {
            end = new CountDownLatch(count);
            for (IExecute<T> countDown : executes) {
                CountDownThread countDownThread = new CountDownThread(threadPool, data, countDown, end);
                countDownThread.execute();
            }
            try {
                end.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        isExecute = true;
        return this;
    }

    public List<T> getData() {
        if(!isExecute)
            throw new RuntimeException("no execute !");
        return data;
    }

}
