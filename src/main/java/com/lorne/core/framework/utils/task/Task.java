package com.lorne.core.framework.utils.task;


//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yuliang on 2016/4/28.
 */
public  class Task {

//    private Lock lock ;
//
//    private Condition condition ;


    private volatile IBack back;

    private volatile Object obj;

    private volatile IBack execute;

    private volatile boolean hasExecute = false;


    /**
     * 是否被唤醒
     */
    private volatile boolean isNotify = false;


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
    private volatile int state = 0;


    /**
     * 是否被使用
     */
    private volatile int isUsed = 0;


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
        return !ConditionUtils.getInstance().hasKey(getKey());
    }


    public boolean isAwait() {
        return isAwait;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
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
//        lock = new ReentrantLock();
//        condition = lock.newCondition();
    }

    private void init(){
        if(isAwait){
            throw new RuntimeException("no notify .");
        }

        isNotify = false;
        isAwait = false;
        key = null;
        state = 0;
        hasExecute = false;
        back=null;
        obj = null;
        execute = null;
        isUsed = 0;
    }


    public synchronized Object execute(IBack back){
        try {
            execute = back;
            hasExecute = true;
            executeSignalTask();
            while (execute!=null){
                try {
                    Thread.sleep(1);
                }catch (Exception e){}
            }
            return obj;
        }finally {
            obj = null;
        }
    }

    public void remove(){
        ConditionUtils.getInstance().removeKey(getKey());
        init();
    }

    private void executeSignalTask() {
        while (!isAwait()){}
//        try {
//            lock.lock();
//            condition.signal();
//        } finally {
//            lock.unlock();
//        }
        synchronized (this){
            notify();
        }
    }

    public void signalTask() {
        while (hasExecute) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!isAwait()){}
//        try {
//            lock.lock();
//            isNotify = true;
//            condition.signal();
//        } finally {
//            lock.unlock();
//        }
        synchronized (this){
            isNotify = true;
            notify();
            isAwait = false;
        }
    }

//    public void signalTask(IBack back) {
//        while (hasExecute) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        while (!isAwait()){}
////        try {
////            lock.lock();
////            isNotify = true;
////            try {
////                back.doing();
////            }catch (Throwable e){}
////            condition.signal();
////        } finally {
////            lock.unlock();
////        }
//
//        synchronized (this){
//            isNotify = true;
//            try {
//                back.doing();
//            } catch (Throwable throwable) { }
//            notify();
//            isAwait = false;
//        }
//    }


    private void waitTask() throws Throwable{
        //condition.await();
        synchronized (this){
            wait();
        }

        if(hasExecute){
            try {
                obj = execute.doing();
            }catch (Throwable e){
                e.printStackTrace();
                obj = e;
            }
            hasExecute = false;
            execute = null;
            waitTask();
        }
    }

    public void awaitTask() {
//        try {
//            lock.lock();
//            isAwait = true;
//            waitTask();
//        } catch (Throwable e) {
//        } finally {
//            lock.unlock();
//        }
//
        synchronized (this){
            try {
                isAwait = true;
                waitTask();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }



//    public void awaitTask(IBack back) {
////        try {
////            lock.lock();
////            try {
////                back.doing();
////            }catch (Throwable e){}
////            isAwait = true;
////            waitTask();
////        } catch (Throwable e) {
////        } finally {
////            lock.unlock();
////        }
//
//        synchronized (this){
//            try {
//                back.doing();
//            }catch (Throwable e){
//                e.printStackTrace();
//            }
//            isAwait = true;
//            try {
//                waitTask();
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
