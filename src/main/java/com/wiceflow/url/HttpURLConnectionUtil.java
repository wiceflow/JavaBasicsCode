package com.wiceflow.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author BF
 * @date 2018/8/19
 *      调用环信API 工具类
 */
public enum HttpURLConnectionUtil {
    TOKEN;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String urlPath = "http://a1.easemob.com/";

    HttpURLConnectionUtil(){
        Properties properties = new Properties();
        System.out.println(HttpURLConnectionUtil.class.getResourceAsStream("/conf.properties"));
        InputStream in = HttpURLConnectionUtil.class.getResourceAsStream("/conf.properties");
        try {
            // 使用properties对象加载输入流
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = properties.getProperty("username");
        System.out.println(s);
    }

    public Map<String,String> getToken(){
        Map<String,String> map = new HashMap<>(5);


        return map;
    }


}
