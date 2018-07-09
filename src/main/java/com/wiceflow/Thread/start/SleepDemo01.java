package com.wiceflow.Thread.start;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BF on 2017/9/
 * 倒计时
 * 1.倒数10个数,一秒内打印一个
 * 2.倒计时
 */
public class SleepDemo01 {
    public static void main(String[] args) throws InterruptedException {
        // 获取当前系统时间 +  10秒
        Date endTime = new Date(System.currentTimeMillis() + 10*1000);
        // 将系统时间转换成长整数用作判断
        long end = endTime.getTime();
        while(true){
            // 输出
            System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
            // 构建新的时间
            endTime = new Date(endTime.getTime()-10000);
            // 休眠1秒
            Thread.sleep(100);
            if (end-10000>endTime.getTime()){
                break;
            }
        }

        //int num = 10;
        //while(true){
        //    System.out.println(num--);
        //    // 暂停
        //    Thread.sleep(1000);
        //    if (num<=0){
        //        break;
        //    }
        //}
    }

    public static void text() throws InterruptedException {
        int num = 10;
        while (true) {
            System.out.println(num--);
            // 暂停
            Thread.sleep(1000);
            if (num <= 0) {
                break;
            }
        }
    }
}


