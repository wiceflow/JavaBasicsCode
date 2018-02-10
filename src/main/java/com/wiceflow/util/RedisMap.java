package com.wiceflow.util;

import java.util.*;

/**
 * http://blog.csdn.net/kissmylove84/article/details/50971137
 * 模仿redis的过期key
 *
 * @author zhengshaorong
 * @date 2018/1/23
 */
public class RedisMap {
    /**
     * key,值
     **/
    private static Map<String, Object> keyvalue = new HashMap<>();
    /**
     * key,timestamp
     **/
    private static Map<String, Long> keytime = new HashMap<>();
    /**
     * 10分钟
     **/
    private static final long EXPIRATIONTIME = 600000;
    /**
     * 设置执行开始时间
     **/
    private static final int START = 0;
    /**
     * 设置间隔执行时间:单位/毫秒,10分钟
     **/
    private static final int INTERVAL = 600000;

    public static void put(String key, Object vale) {
        keyvalue.put(key, vale);
        keytime.put(key, System.currentTimeMillis());
    }

    public static Object get(String key) {
        return keyvalue.get(key);
    }

    public static void remove(String key) {
        keyvalue.remove(key);
        keytime.remove(key);
    }

    static {
        Timer tt = new Timer();//定时类
        //创建一个定时任务,从0秒开始，每隔N秒执行一次
        tt.schedule(new TimerTask() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();//获取系统时间
                Iterator<Map.Entry<String, Long>> entries = keytime.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry) entries.next();
                    String key = (String) entry.getKey();
                    long value = (Long) entry.getValue();
                    //获取当前时间跟存入时间的差值
                    long dif = now - value;
                    //判断时间是否已经过期  如果过期则清楚key 否则不做处理
                    if (dif > EXPIRATIONTIME) {
                        keyvalue.remove(key);
                        entries.remove();
                        System.out.println("cn.sibat.subway.fare.common.RedisMap------->key:" + key + " 已过期,清空");
                    }
                }
            }
        }, START, INTERVAL);
    }

    public static void main(String[] args) {
        RedisMap.put("1", "kk");
        while (true) {
            System.out.println(keyvalue);
            System.out.println(keytime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
