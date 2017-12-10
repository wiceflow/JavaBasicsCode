package com.wiceflow.http.retrofit2;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiceflow.json.fastjson.po.Basic;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by BF on 2017/12/10.
 * 测试接口是否正常运行
 */
public class Test {
    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .build();

        IndexSystemHttpJSON ishj = retrofit.create(IndexSystemHttpJSON.class);

        Call<ResponseBody> call = ishj.getJSON();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = new String(response.body().bytes());
                    System.out.println(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });



    }
}
