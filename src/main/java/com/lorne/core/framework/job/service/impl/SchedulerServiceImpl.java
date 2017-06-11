package com.lorne.core.framework.job.service.impl;


import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.job.service.SchedulerService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private Scheduler sched = null;


    public SchedulerServiceImpl() {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            sched = sf.getScheduler();
        } catch (Exception e) {

        }
    }

    @Override
    public void start() {
        try {
            if (sched != null)
                sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (sched != null)
                sched.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public JobKey addSchedulerByRunDate(Date runDate, Class<? extends Job> jobClazz, String data) throws ServiceException {
        if (sched == null) {
            throw new ServiceException("scheduler is null ");
        }
        JobDetail job = JobBuilder.newJob(jobClazz).build();
        job.getJobDataMap().put("data", data);
        Trigger trigger = newTrigger().startAt(runDate).build();
        JobKey jobKey = null;
        try {
            sched.scheduleJob(job, trigger);
            jobKey = job.getKey();
        } catch (SchedulerException e) {
            throw new ServiceException(e);
        }
        return jobKey;
    }

    @Override
    public JobKey addRepeatDayScheduler(Class<? extends Job> jobClazz, int hour, String data) throws ServiceException {
        if (sched == null) {
            throw new ServiceException("scheduler is null ");
        }
        JobDetail job = JobBuilder.newJob(jobClazz).build();
        CronTrigger trigger = newTrigger().withIdentity(jobClazz.getName(), "repeatday_group").
                withSchedule(CronScheduleBuilder.cronSchedule("0 0 " + hour + " * * ?")).startAt(new Date()).build();
        JobKey jobKey = null;
        try {
            sched.scheduleJob(job, trigger);
            jobKey = job.getKey();
        } catch (SchedulerException e) {
            throw new ServiceException(e);
        }
        return jobKey;
    }

    @Override
    public boolean deleteJob(JobKey jobKey) throws ServiceException {
        if (sched == null) {
            throw new ServiceException("scheduler is null ");
        }
        try {
            sched.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteAllJob() throws ServiceException {
        if (sched == null) {
            throw new ServiceException("scheduler is null ");
        }
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = null;
        try {
            jobKeys = sched.getJobKeys(matcher);
        } catch (SchedulerException e) {
            throw new ServiceException(e);
        }
        if (null != jobKeys) {
            for (JobKey jobKey : jobKeys) {
                deleteJob(jobKey);
            }
        }
        return true;
    }
}
