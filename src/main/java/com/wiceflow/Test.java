package com.wiceflow;

import com.wiceflow.url.HttpURLConnectionUtil;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
        String s = "         ";
        System.out.println(s.trim().length());

        //String s1 = HttpURLConnectionUtil.TOKEN.getToken();
        //System.out.println(s1);

        String s2 = HttpURLConnectionUtil.TOKEN.getChatgroupsID("问","aaa"
                ,"13824835722",HttpURLConnectionUtil.TOKEN.getToken());
        System.out.println(s2);


    }
}