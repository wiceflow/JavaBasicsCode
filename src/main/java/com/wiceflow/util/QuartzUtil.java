package com.wiceflow.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by BF on 2017/12/14.
 * Quartz作业调度工具类
 */
public class QuartzUtil {
    private static SchedulerFactory sf;
    // 无法在静态方法中创建
    private static Scheduler scheduler;
    private static QuartzUtil quartzUtil;

    /**
     * 单例
     * @throws SchedulerException
     */
    private QuartzUtil() throws SchedulerException {
        sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();
    }
    public static  QuartzUtil getInstance() throws SchedulerException {
        if (quartzUtil==null){
            synchronized (QuartzUtil.class){
                if (quartzUtil==null){
                    quartzUtil = new QuartzUtil();
                }
            }
        }
        return quartzUtil;
    }

    /**
     * 创建JobDetail
     * @param T [T.Class] 控制任务的Class
     * @param jobNum [String] 任务分组
     * @param groupNum [String] 分组名
     * @return 返回 [JobDetail]
     */
    public JobDetail getJobDetail(Class T, String jobNum, String groupNum){
        JobDetail job = newJob(T).withIdentity(jobNum,groupNum).build();
        return job;
    }

    /**
     * 创建 Trigger 目前只有这一个Trigger工具
     * @param triggerGroup [String] Trigger分组
     * @param group [String] group 分组
     * @param c [CronScheduleBuilder] 对象
     * @return 返回 [Trigger]
     */
    public Trigger getTrigger(String triggerGroup, String group, CronScheduleBuilder c){
        Trigger trigger = newTrigger().withIdentity(triggerGroup, group).startNow()
                .withSchedule(c).build();
        return trigger;
    }

    public void scheduleJob(JobDetail job,Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(job,trigger);
    }
    public void startSchedule() throws SchedulerException {
        scheduler.start();
    }
}
