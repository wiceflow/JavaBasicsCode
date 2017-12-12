package com.wiceflow.frame.quartz2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by BF on 2017/12/11.
 */
public class SimpleExample {
    public void run22() throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();

        Scheduler sched = sf.getScheduler();

        // 通过过JobDetail封装RunJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
        JobDetail job = newJob(RunJob.class).withIdentity("job1", "group1").build();

        // 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 22 ? * MON");
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
                .withSchedule(
                        cronScheduleBuilder
                ).build();

        // 注册并进行调度
        sched.scheduleJob(job, trigger);
        SchedulerContext sc = sched.getContext();
        // 启动调度器
        sched.start();


    }

    public static void main(String[] args) throws Exception {
        SimpleExample example = new SimpleExample();
        example.run22();
    }
}
