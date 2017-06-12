package com.lorne.core.framework.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by lorne on 2017/6/12.
 */
public abstract class BaseJob implements Job {

    public abstract void execute(String data);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String data = context.getJobDetail().getJobDataMap()
                .getString("data");
        execute(data);
    }

}
