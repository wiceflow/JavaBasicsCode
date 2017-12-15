package com.wiceflow.http.retrofit2;
import com.wiceflow.json.fastjson.po.General;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by BF on 2017/12/10.
 * 北斗--指数系统
 * Retrofit获取Http JSON数据接口
 */
public interface IndexSystemHttpJSON {

    // 这的POST URL会拼上Retrofit中的Url
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    // 这里表示这是一个Form表单的意思 因为这里的例子是翻译，所有要传输一个字段到服务器，用@Field标记
    @FormUrlEncoded
    // 这里的返回值是一个JavaBean类 具体要返回什么类型的在Call中设置泛型
    Call<General> getJSONByForm(@Field("i") String targetSentence);

    //@POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    @POST("TrafficIndexFusion/traffic/GetWeekReportData")
    // 这里返回的是Body结构体，可以取出字符串，例如你不想让retrofit帮你解析JSON字符串，就可以用这个返回值
    // String str = new String (ResponseBody.body.bytes())
    Call<ResponseBody> getJSON(@Field("date") String date);
}
