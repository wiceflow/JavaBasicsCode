package com.wiceflow.url;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author BF
 * @date 2018/8/19
 *      调用环信API 工具类
 */
public enum HttpURLConnectionUtil {
    /**
     *
     */
    TOKEN;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Properties properties;
    HttpURLConnectionUtil(){
        InputStream in = HttpURLConnectionUtil.class.getResourceAsStream("/conf.properties");
        properties = new Properties();
        try {
            // 使用properties对象加载输入流
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = properties.getProperty("username");
        System.out.println(s);
    }

    /**
     * 获取环信Token
     * @return        [String] 返回token
     */
    public String getToken() {
        // post 请求的参数
        Map<String,Object> map = new HashMap<>(5);
        map.put("grant_type",properties.getProperty("Easemob.grant_type"));
        map.put("client_id",properties.getProperty("Easemob.client_id"));
        map.put("client_secret",properties.getProperty("Easemob.client_secret"));
        // orgName [String] 企业名标志   appName [String] App名
        JSONObject jsonObject =
                initializationHttpURLConnerction(properties.getProperty("Essemob.org_name"),properties.getProperty("Essemob.app_name"),map,"token",null);
        if (jsonObject != null){
            // 返回token
            return jsonObject.get("access_token").toString();
        }
        return null;
    }

    /**
     * 创建群聊并获取群聊ID
     * @param missionName   [String] 群聊名称
     * @param desc          [String] 群聊描述
     * @param owner         [String] 群聊创建者
     * @param token         [String] Token
     * @return              [String] 群聊ID
     *
     *          propertis 获取内容时候无法区分是否布尔值  -- 或者是我不会
     */
    @SuppressWarnings("unchecked")
    public String getChatgroupsID(String missionName,String desc,String owner,String token){
        // post 请求的参数
        Map<String,Object> map = new HashMap<>(5);
        // 群组名称
        map.put("groupname",missionName);
        // 群组描述
        map.put("desc",desc);
        map.put("public",isTrue(properties.getProperty("Easemob.public")));
        map.put("approval",isTrue(properties.getProperty("Essemob.approval")));
        map.put("maxusers",properties.getProperty("Easemob.maxusers"));
        map.put("owner",owner);
        map.put("members",new ArrayList<>());
        JSONObject jsonObject =
                initializationHttpURLConnerction(properties.getProperty("Essemob.org_name"),properties.getProperty("Essemob.app_name"),map,"chatgroups",token);
        if (jsonObject != null){
            Map<String,String> data = (Map<String, String>) jsonObject.get("data");
            if (data != null){
                return data.get("groupid");
            }
        }
        return null;
    }


    /**
     * 组装URL 创建HTTPConnection
     * @param orgName [String] 企业名标志
     * @param appName [String] App名
     * @param type    [String] 类型 {
     *                                  token，chatgroups ....
     *                              }
     * @param params  [Map]    参数
     * @param token   [String] token  若带了token，则为获取环信群组ID
     * @return        [JSONObject]    返回http的响应体
     */
    @SuppressWarnings("unchecked")
    private JSONObject initializationHttpURLConnerction(String orgName,String appName,Map<String,Object> params,String type,String token) {
        String urlPath = "http://a1.easemob.com/" + orgName + "/" + appName + "/" + type;
        DataOutputStream dos = null;
        try {
            // 建立连接
            URL url = new URL(urlPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            //需要输出
            httpConn.setDoOutput(true);
            // 需要输入
            httpConn.setDoInput(true);
            // 不允许缓存
            httpConn.setUseCaches(false);
            // 设置POST方式连接 默认为GET
            httpConn.setRequestMethod("POST");
            // 设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/json");
            // 维持长连接
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            // 设置字符编码
            httpConn.setRequestProperty("Charset", "UTF-8");
            if (token != null) {
                token = "Bearer " + token;
                httpConn.setRequestProperty("Authorization", token);
            }
            // 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpConn.connect();
            // 建立输入流，向指向的URL传入参数
            dos = new DataOutputStream(httpConn.getOutputStream());
            // 将参数转成字节流传递  （这里使用了fastJSON）
            String jsonParams = JSON.toJSONString(params);
            dos.writeBytes(jsonParams);
            dos.flush();
            // 获得响应状态
            int resultCode = httpConn.getResponseCode();
            if(resultCode == HttpURLConnection.HTTP_OK) {
                // 拼接返回的数据
                StringBuilder sb = new StringBuilder();
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                String readLine;
                while ((readLine = responseReader.readLine()) != null){
                    sb.append(readLine);
                }
                if (!sb.toString().isEmpty()){
                    return JSON.parseObject(sb.toString());
                }
            }
        } catch (ProtocolException e) {
            logger.error("设置请求方式为POST失败");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            logger.error("url路劲错误");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("建立HttpURLConnection失败or建立输入流失败");
            e.printStackTrace();
        }finally {
            try {
                assert dos != null;
                dos.close();
            } catch (IOException e) {
                logger.error("关闭输入参数流失败");
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断 properties值是否为true
     * @param str [String] properties里面的值
     * @return    [Boolean]
     */
    private boolean isTrue(String str){
        return "true".equals(str);
    }
}
