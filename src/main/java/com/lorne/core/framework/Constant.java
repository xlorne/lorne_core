package com.lorne.core.framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by yuliang on 2015/7/8.
 */
public class Constant {


    public final static int defaultSize = 20;


    public final static String forwardKey = "asdsnfgdkfgfkgh";

    public final static String forwardValue = "fgghjsdfdfgfghfgh";


    public static ScheduledExecutorService scheduledExecutorService = null;


    static {
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }


}
