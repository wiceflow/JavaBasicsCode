package com.wiceflow;

import com.alibaba.fastjson.JSON;
import com.wiceflow.url.HttpURLConnectionUtil;
import com.wiceflow.util.DateUtil;
import com.wiceflow.util.StringUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {

//    private static final int COUNT_BITS = Integer.SIZE - 3;
//    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
//
//    private static final int RUNNING    = -1 << COUNT_BITS;
//    private static final int SHUTDOWN   =  0 << COUNT_BITS;
//    private static final int STOP       =  1 << COUNT_BITS;
//    private static final int TIDYING    =  2 << COUNT_BITS;
//    private static final int TERMINATED =  3 << COUNT_BITS;
//    public static void main(String[] args) throws ParseException {
//        final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//        int b = runStateOf(RUNNING);
//        int c = workerCountOf(ctl.get());
//        System.out.println(ctl.get());
//        // 11100000000000000000000000000000
//        System.out.println("RUNNING 二进制 ： " + Integer.toBinaryString(RUNNING));
//        // 11111111111111111111111111111
//        System.out.println("CAPACITY 二进制 ： " + Integer.toBinaryString(CAPACITY));
//        System.out.println(SHUTDOWN);
//    }
//    private static int runStateOf(int c)     { return c & ~CAPACITY; }
//
//    private static int workerCountOf(int c) {
//        return c & CAPACITY;
//    }
//    private static int ctlOf(int rs, int wc) { return rs | wc; }
//
//    private static boolean isRunning(int c) {
//        return c < SHUTDOWN;
//    }


//    public static void main(String[] args) {
//        People people = new People();
//        people.setName("bf");
//        System.out.println(people.getName());
//        System.out.println(people.getAng());
//    }

    public static void main(String[] args) {
//        Map<String,People> map = new HashMap<>(2);
//        People people = new People();
//        people.setName("a");
//        people.setAng("2");
//        map.put("22",people);
//
//        People people1 = map.get("22");
//        people1.setName("hahah");
//
//        People people2 = map.get("22");
//
//        System.out.println(people.getName());
        Scanner sreader = new Scanner(System.in);

        String string = sreader.nextLine();
        System.out.println(string);
    }

}
class People{
    String name;
    String ang;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAng() {
        return ang;
    }

    public void setAng(String ang) {
        this.ang = ang;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", ang='" + ang + '\'' +
                '}';
    }
}