package com.wiceflow.redis;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author BF
 * @date 2018/10/10
 */
public class JedisUtil {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        //zListTest(jedis);
        mapTset(jedis);
    }


    public static void mapTset(Jedis jedis){
        Map<String,String> people = new HashMap<>(3);
        people.put("name","iceflow");
        people.put("age","23");
        people.put("phone","13824835722");
        jedis.hmset("People",people);
        // 这样是可以将Map拿出来的
        Map<String,String> map = jedis.hgetAll("People");
        // 这个获取的是一个列表 因为Map可能有多个
        List<String> name  = jedis.hmget("People","name");
    }

    /**
     * 有序集合
     * @param jedis
     */
    public static void zListTest(Jedis jedis) {
        // 将一个带有给定分值的成员添加到有序集合里面  分值用来排序
        jedis.zadd("zsetTest", 10, "a");
        jedis.zadd("zsetTest", 20, "b");
        // 获取有序集合在给定分值范围内的所有元素
        Set zset = jedis.zrangeByScore("zsetTest", 5, 10);
        Iterator iterator = zset.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("........");
        // 根据分值的排序顺序，获取有序集合在给定位置范围内的所有元素   -1代表无穷
        Set zset1 = jedis.zrange("zsetTest", 0, -1);
        Iterator iterator1 = zset1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    /**
     * redis处理散列
     *
     * @param jedis 注意区分还有一个 hmset 这是存储一个map结构的
     */
    public static void hashTest(Jedis jedis) {
        // 设置键值对    value只能为String
        jedis.hset("hashTest", "a", "b");
        jedis.hset("hashTest", "b", "c");
        jedis.hset("hashTest", "c", "a");
        // 获取 hash 中的所有键值对
        Map map = jedis.hgetAll("hashTest");
        // 根据 key 获取 value
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));
        System.out.println("删除后...");
        jedis.hdel("hashTest", "a");
        Map map1 = jedis.hgetAll("hashTest");
        System.out.println(map1.get("a"));
        System.out.println(map1.get("b"));
        System.out.println(map1.get("c"));
    }

    /**
     * set 操作
     *
     * @param jedis 在redis中有一个方法 srem 判断一个元素是否存在于set中，若存在，则删除
     */
    public static void setTest(Jedis jedis) {
        // 将给定元素添加到集合
        jedis.sadd("setTest", "a");
        jedis.sadd("setTest", "b");
        jedis.sadd("setTest", "a");

        // 检查给定元素是否存在于集合中
        System.out.println("a is exist in setTest ?" + jedis.sismember("setTest", "a"));

        Set set = jedis.smembers("setTest");
        // 返回集合包含的所有元素
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 如果给定的元素存在于集合中，那么移除这个元素   srem
        jedis.srem("setTest", "a");

        Set finalSet = jedis.smembers("setTest");
        Iterator iterator1 = finalSet.iterator();
        System.out.println("删除后集合中元素...");
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    /**
     * 测试字符串
     *
     * @param jedis
     */
    public static void stringTest(Jedis jedis) {
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        String myKey = jedis.get("myKey");
        System.out.println("foo = " + value);
        System.out.println("myKey = " + myKey);

        // 删除操作
        jedis.del("foo");
        System.out.println("foo = " + jedis.get("foo"));
    }

    /**
     * 测试可重复列表list
     *
     * @param jedis
     */
    public static void listTest(Jedis jedis) {
        // 添加列表  注意list是可重复的，多次执行会往redis列表中重复插入此数据
        jedis.lpush("nameList", "bf");
        jedis.lpush("nameList", "yn");
        jedis.lpush("nameList", "iceflow");
        // 三个参数分别为  列表名字  从第几个元素开始  第几个元素结束
        List<String> nameList = jedis.lrange("nameList", 1, 3);
        System.out.println(nameList.toString());
    }
}
