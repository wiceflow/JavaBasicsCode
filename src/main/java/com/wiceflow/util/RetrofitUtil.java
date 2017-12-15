package com.wiceflow.util;
import com.wiceflow.http.retrofit2.IndexSystemHttpJSON;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BF on 2017/12/15.
 * 封装Retrofit方法 减少创建
 * 目前只封装了对ResponseBody的请求方法，请求具体PO的方法快要重新封装Retrofit实例
 * 添加解析器 .addConverterFactory(GsonConverterFactory.create())
 */
public class RetrofitUtil<T> {
    // Map数组存储其对象
    private static Map<String, Retrofit> retrofitMap = new HashMap<>();
    private RetrofitUtil(){

    }

    /**
     * 返回一个Retrofit实例
     * @param url [String] 获取参数的地址
     * @return 返回一个Retrofit实例
     */
    public static Retrofit getRetrofit(String url){
        if (retrofitMap!=null){
            for (String mapUrl:retrofitMap.keySet()){
                if (url.equals(mapUrl)){
                    return retrofitMap.get(url);
                }
            }
        }else {
            // 预留空间
        }
        // 创建Retrofit实例 设置url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        retrofitMap.put(url,retrofit);
        return retrofit;
    }

    /**
     * 返回一个IndexSystemHttpJSON实例
     * @param url [String] 获取参数地址
     * @param T [网络接口]
     * @return 返回一个网络接口实例
     */
    public static IndexSystemHttpJSON getIndexSystemHttpJSON(String url, Class T){
        return (IndexSystemHttpJSON) getRetrofit(url).create(T);
    }

    /**
     * 返回一个Call对象
     * @param url [String] 获取参数的地址
     * @param T [Class] 网络接口
     * @param date [String] 表单参数
     * @return 返回一个可发送请求的Call对象
     */
    public static Call<ResponseBody> getCallResponseBody(String url, Class T, String date){
        return getIndexSystemHttpJSON(url,T).getJSON(date);
    }

}
