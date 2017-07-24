package com.lorne.core.framework.utils.task;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yuliang on 2016/4/28.
 */
public  class Task {

    private Lock lock ;

    private Condition condition ;

    private volatile IBack back;


    private Object obj;

    private volatile IBack execute;

    private volatile boolean hasExecute = false;


    /**
     * 是否被唤醒
     */
    private volatile boolean isNotify = false;

    /**
     * 是否被唤醒
     */
    private volatile boolean isRemove = false;

    /**
     *  是否执行等待
     */
    private volatile boolean isAwait = false;


    /**
     * 唯一标示key
     */
    private String key;

    /**
     * 数据状态用于业务处理
     */
    private int state = 0;




    /**
     * 是否被唤醒
     * @return true 是，false，否
     */
    public boolean isNotify() {
        return isNotify;
    }

    /**
     * 是否被移除 true 是 false 否
     * @return
     */
    public boolean isRemove() {
        return isRemove;
    }


    public boolean isAwait() {
        return isAwait;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public IBack getBack() {
        return back;
    }

    public void setBack(IBack back) {
        this.back = back;
    }

    protected Task() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }


    public synchronized Object  execute(IBack back) throws Throwable{
        try {
            execute = back;
            hasExecute = true;
            executeSignalTask();
            while (execute!=null){
                Thread.sleep(1);
            }
            return obj;
        }finally {
            obj = null;
        }
    }

    public void remove(){
        ConditionUtils.getInstance().removeKey(getKey());
        isRemove = true;
    }

    private void executeSignalTask() {
        while (!isAwait()){}
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void signalTask() {
        if (!hasExecute) {
            while (!isAwait()){}
            try {
                lock.lock();
                isNotify = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }else{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void signalTask(IBack back) {
        if (!hasExecute) {
            while (!isAwait()){}
            try {
                lock.lock();
                isNotify = true;
                back.doing();
                condition.signal();
            } catch (Throwable e) {
            } finally {
                lock.unlock();
            }
        }else{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    private void waitTask() throws Throwable{
        condition.await();
        if(hasExecute){
            try {
                obj = execute.doing();
            }catch (Throwable e){
                obj = e;
            }
            hasExecute = false;
            execute = null;
            waitTask();
        }
    }

    public void awaitTask() {
        try {
            lock.lock();
            isAwait = true;
            waitTask();
        } catch (Throwable e) {
        } finally {
            lock.unlock();
        }
    }



    public void awaitTask(IBack back) {
        try {
            lock.lock();
            back.doing();
            isAwait = true;
            waitTask();
        } catch (Throwable e) {
        } finally {
            lock.unlock();
        }
    }
}
