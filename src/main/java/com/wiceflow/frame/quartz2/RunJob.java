package com.wiceflow.frame.quartz2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import static com.wiceflow.http.retrofit2.BDindex.getData;
import static com.wiceflow.util.TimeUtil.returnIntDate;


/**
 * Created by BF on 2017/12/11.
 * 时间调度Job类
 */
public class RunJob implements Job {
    public RunJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String date = String.valueOf(returnIntDate());
        System.out.println(date);
        getData("20171212",true);
    }
}
