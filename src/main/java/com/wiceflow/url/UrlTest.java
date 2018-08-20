package com.wiceflow.url;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author BF
 * @date 2018/8/19
 * 测试外部API调用
 */
public class UrlTest {

    public static void main(String args[]) throws IOException {

        String urlPath = "http://a1.easemob.com/1161180522177157/emergencysecurity/token";
        Param param = new Param();
        param.setGrant_type("client_credentials");
        param.setClient_id("YXA6QeOkkGOtEeiS5c3cG5vPcQ");
        param.setClient_secret("YXA653W-s3uWHxv7E9Q0fRBzVrDGWz4");

        String paramString = JSON.toJSONString(param);


        //建立连接
        URL url=new URL(urlPath);
        HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();

        //设置参数 //需要输出
        httpConn.setDoOutput(true);
        //需要输入
        httpConn.setDoInput(true);
        // 不允许缓存
        httpConn.setUseCaches(false);
        // 设置POST方式连接 默认为false
        httpConn.setRequestMethod("POST");

        // 设置请求属性
        httpConn.setRequestProperty("Content-Type", "application/json");
        // 维持长连接
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("Charset", "UTF-8");

        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();

        //建立输入流，向指向的URL传入参数
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
        dos.writeBytes(paramString);
        dos.flush();
        dos.close();

        //获得响应状态
        int resultCode=httpConn.getResponseCode();
        if(HttpURLConnection.HTTP_OK==resultCode){
            StringBuilder sb=new StringBuilder();
            String readLine =new String();
            BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            while((readLine=responseReader.readLine())!=null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            System.out.println(sb.toString());
        }
    }
}
