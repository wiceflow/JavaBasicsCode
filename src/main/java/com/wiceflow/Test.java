package com.wiceflow;
import com.wiceflow.json.fastjson.po.AllTrafficColumn;
import org.quartz.SchedulerException;

import java.text.ParseException;
import static com.wiceflow.http.retrofit2.BDindex.getData;
import static com.wiceflow.http.retrofit2.BDindex.timer;


/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) throws ParseException, SchedulerException {
        //System.out.println(String.valueOf((char) 29));

        getData("20171201",false);
        //timer();
    }
}
