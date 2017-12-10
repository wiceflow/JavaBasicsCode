package com.wiceflow.http.retrofit2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by BF on 2017/12/10.
 * 北斗--指数系统
 * Retrofit获取Http JSON数据接口
 */
public interface IndexSystemHttpJSON {

    @POST("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<ResponseBody> getJSON();
}
