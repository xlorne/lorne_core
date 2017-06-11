package com.lorne.core.framework.job.service;


import com.lorne.core.framework.exception.ServiceException;
import org.quartz.Job;
import org.quartz.JobKey;

import java.util.Date;

public interface SchedulerService {

    /**
     * 启动定时任务
     */
    void start();

    /**
     * 关闭定时任务
     */
    void close();

    /**
     * 添加定时任务
     * @param runDate    执行日期
     * @param jobClazz   任务Job
     * @param data       执行数据
     * @return           任务Key
     * @throws ServiceException
     */
    JobKey addSchedulerByRunDate(Date runDate, Class<? extends Job> jobClazz, String data) throws ServiceException;

    /**
     * 添加重复循环任务
     * @param jobClazz  任务Job
     * @param hour      间隔时间（小时）
     * @param data      执行数据
     * @return          任务Key
     * @throws ServiceException
     */
    JobKey addRepeatDayScheduler(Class<? extends Job> jobClazz, int hour, String data) throws ServiceException;

    /**
     * 删除任务
     * @param jobKey
     * @return
     * @throws ServiceException
     */
    boolean deleteJob(JobKey jobKey) throws ServiceException;


    /**
     * 删除全部任务
     * @return
     * @throws ServiceException
     */
    boolean deleteAllJob() throws ServiceException;
}
