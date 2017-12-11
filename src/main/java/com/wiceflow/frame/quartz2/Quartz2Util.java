package com.wiceflow.frame.quartz2;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by duxin on 2017/12/11.
 * Quartz2工具类
 * 懒汉式单利创建
 */
public class Quartz2Util {
    private SchedulerFactory sf;
    private Scheduler scheduler;
    // 确保对象存在
    private static Quartz2Util instance;

    // 私有化构造器
    private Quartz2Util() throws SchedulerException {
        // 通过SchedulerFactory获取一个调度器实例
        sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();
    }

    // 获取对象
    public static Quartz2Util getInstance() throws SchedulerException {
        if (null == instance) {
            synchronized (Quartz2Util.class) {
                if (null == instance) {
                    instance = new Quartz2Util();
                }
            }
        }
        return instance;
    }
    // 获取SchedulerFactory对象
    public SchedulerFactory getSchedulerFactory() {
        return sf;
    }
    // 获取Scheduler对象
    public Scheduler getScheduler() {
        return scheduler;
    }
}
