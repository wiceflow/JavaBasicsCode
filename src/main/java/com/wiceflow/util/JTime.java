package com.wiceflow.util;


import java.util.*;


/**
 * @author BF
 * @date 2018/2/27
 * http://blog.csdn.net/kissmylove84/article/details/50971137
 * Java 定时过期类  模仿redis的过期key
 * 参考 zhengshaorong
 */
public class JTime {
    /**
     * key值 用来储存需要进行定时任务的对象
     */
    public static Map<String, Object> keyValue = new HashMap<>();

    /**
     * 用来储存对应对象的过期时间
     */
    public static Map<String, Long> keyTime = new HashMap<>();

    /**
     * 过期时间
     */
    public static final long EXPIRYTIME = 60000;

    /**
     * 开始时间  取消魔法值
     */
    public static final long STARTTIME = 0;

    /**
     * 间隔时间 表示隔多久进行一次更新
     */
    private static final long INTERVALTIME = 1000;

    /**
     * 设置过期任务
     *
     * @param key   为定时任务设置一个键值
     * @param value 任务数据
     */
    public static void set(String key, Object value) {
        // 将需要定时的任务保存到map中
        keyValue.put(key, value);
        // 对应任务的开始时间
        keyTime.put(key, System.currentTimeMillis());
    }

    /**
     * 判断对应键值是否存在任务，如果存在则不允许继续设置过期任务
     *
     * @param key 键值
     * @return
     */
    public static Object get(String key) {
        return keyValue.get(key);
    }

    /**
     * 移除任务  发生在过期任务自动执行完成的时候
     *
     * @param key 键值
     */
    public static void remove(String key) {
        keyValue.remove(key);
        keyTime.remove(key);
    }

    /**
     * 定时任务运行
     */
    static {
        // 定时类
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                // 任务开始系统时间
                long starTime = System.currentTimeMillis();
                // 对象过期时间的Map 迭代这个对象，开始过期任务
                Iterator<Map.Entry<String, Long>> entryIterator = keyTime.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, Long> entry = entryIterator.next();
                    String key = entry.getKey();
                    long expiryTime = entry.getValue();
                    // 时间差值
                    long dif = starTime - expiryTime;
                    //判断时间是否已经过期  如果过期则清楚key 否则不做处理
                    if (dif > EXPIRYTIME) {
                        keyValue.remove(key);
                        entryIterator.remove();
                        System.out.println("对应键值：" + key + "的任务已经过期清除");
                    }
                }
            }
        }, STARTTIME, INTERVALTIME);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        JTime.set("1", "kk");
        while (true) {
            System.out.println(keyValue + "1");
            System.out.println(keyTime + "2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
