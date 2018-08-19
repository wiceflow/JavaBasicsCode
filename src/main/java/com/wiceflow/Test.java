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

        HttpURLConnectionUtil.TOKEN.getToken();
    }
}