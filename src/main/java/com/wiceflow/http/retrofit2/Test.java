package com.wiceflow.http.retrofit2;


import com.alibaba.fastjson.JSON;
import com.wiceflow.json.fastjson.po.General;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
/**
 * Created by BF on 2017/12/10.
 * 测试接口是否正常运行
 * 初步测试--利用retro自带的gson会比阿里的fastJson解析要快
 * TODO 加大bean的数据
 */
public class Test {
    public static void main(String[] args) {
        // 创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                // 设置Url 记住 要已/结尾，表示后面拼上Post里面的Url 不以其结尾也行，具体看文章介绍
                .baseUrl("http://fanyi.youdao.com/")
                // 这个是 如果在接口中定义了返回的是Bean类，则一定要配置解析器！不管你用不用他的GSON解析，都要设置
                // 具体有哪几种解析看文章介绍，如果是设置了ResponseBody则不用设置这一栏
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        // 创建 网络请求接口 实例
        IndexSystemHttpJSON ishj = retrofit.create(IndexSystemHttpJSON.class);
        final long startTime1 = System.currentTimeMillis();   // 获取开始时间
        // 对 发送请求 进行封装
        Call<General> call = ishj.getJSONByForm("I love you");

        call.enqueue(new Callback<General>() {
            // 成功函数
            @Override
            public void onResponse(Call<General> call, Response<General> response) {
                // response 就是我们在接口中定义的泛型返回值 如果我们定义了一个bean 则response.body()就表示这个bean
                General t = response.body();
                System.out.println(response.body().toString());
                long endTime = System.currentTimeMillis();// 结束时间
                System.out.println("时间为： " + (endTime-startTime1));
            }

            @Override
            public void onFailure(Call<General> call, Throwable throwable) {
                if (call.isCanceled()) {
                    System.out.println("请求取消");
                } else {
                    System.out.println("请求出错");
                    System.out.println(throwable.getMessage());
                }
            }
        });

        final long startTime2 = System.currentTimeMillis();
        final Call<ResponseBody> call2 = ishj.getJSON("I love you");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = new String(response.body().bytes());
                    Translationl t = JSON.parseObject(s,Translationl.class);
                    System.out.println(t.toString());

                    long endTime = System.currentTimeMillis();
                    System.out.println("时间为： " + (endTime-startTime2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (call.isCanceled()) {
                    System.out.println("请求取消");
                } else {
                    System.out.println("请求出错");
                    System.out.println(throwable.getMessage());
                }
            }
        });
    }

//    public static void main(String[] args) throws SchedulerException {
////        String jsonString = "{\"AMPMPortSpeed\":{\"AMLWSpeed\":29.5,\"AMLYSpeed\":30.7,\"AMSpeed\":29.6,\"AMSpeedMOM\":0.003,\"AMSpeedYOY\":-0.035,\"DistrictName\":\"早晚高峰口岸平均速度\",\"PMLWSpeed\":25.6,\"PMLYSpeed\":26.5,\"PMSpeed\":25.4,\"PMSpeedMOM\":-0.006,\"PMSpeedYOY\":-0.042},\"AMPeakWholeIndex\":2.8,\"AMPeakWholeSpeed\":33.4,\"AMWholeCross\":29.6,\"AMWholeCrossMOM\":-0.024,\"AMWholeCrossYOY\":-0.03,\"ArterialTrafficList\":[{\"AMLWSpeed\":52.3,\"AMLYSpeed\":54.8,\"AMSpeed\":54.2,\"AMSpeedMOM\":0.037,\"ArterialName\":\"南坪快速\",\"PMLWSpeed\":47.7,\"PMLYSpeed\":46.9,\"PMSpeed\":46.9,\"PMSpeedMOM\":-0.016},{\"AMLWSpeed\":37.5,\"AMLYSpeed\":38.9,\"AMSpeed\":38.5,\"AMSpeedMOM\":0.027,\"ArterialName\":\"沙河西路\",\"PMLWSpeed\":30.4,\"PMLYSpeed\":35.3,\"PMSpeed\":31.2,\"PMSpeedMOM\":0.026},{\"AMLWSpeed\":27.5,\"AMLYSpeed\":29,\"AMSpeed\":25.7,\"AMSpeedMOM\":-0.063,\"ArterialName\":\"南海大道\",\"PMLWSpeed\":20.6,\"PMLYSpeed\":23.6,\"PMSpeed\":20.6,\"PMSpeedMOM\":0.001},{\"AMLWSpeed\":51.6,\"AMLYSpeed\":48.3,\"AMSpeed\":51.5,\"AMSpeedMOM\":-0.001,\"ArterialName\":\"北环大道\",\"PMLWSpeed\":41.9,\"PMLYSpeed\":45.6,\"PMSpeed\":41.8,\"PMSpeedMOM\":-0.004},{\"AMLWSpeed\":42.1,\"AMLYSpeed\":42.8,\"AMSpeed\":43,\"AMSpeedMOM\":0.021,\"ArterialName\":\"深南大道\",\"PMLWSpeed\":29.7,\"PMLYSpeed\":33.9,\"PMSpeed\":31.2,\"PMSpeedMOM\":0.05},{\"AMLWSpeed\":24.5,\"AMLYSpeed\":26.5,\"AMSpeed\":25.1,\"AMSpeedMOM\":0.026,\"ArterialName\":\"红岭路\",\"PMLWSpeed\":19.1,\"PMLYSpeed\":21.2,\"PMSpeed\":20.7,\"PMSpeedMOM\":0.083},{\"AMLWSpeed\":39.9,\"AMLYSpeed\":43.6,\"AMSpeed\":40.5,\"AMSpeedMOM\":0.016,\"ArterialName\":\"皇岗路\",\"PMLWSpeed\":28.2,\"PMLYSpeed\":35.1,\"PMSpeed\":30.2,\"PMSpeedMOM\":0.072},{\"AMLWSpeed\":64.1,\"AMLYSpeed\":66,\"AMSpeed\":65,\"AMSpeedMOM\":0.013,\"ArterialName\":\"滨海大道\",\"PMLWSpeed\":57.5,\"PMLYSpeed\":58.8,\"PMSpeed\":56.3,\"PMSpeedMOM\":-0.02},{\"AMLWSpeed\":33.7,\"AMLYSpeed\":35.1,\"AMSpeed\":34.6,\"AMSpeedMOM\":0.025,\"ArterialName\":\"新洲路\",\"PMLWSpeed\":23.5,\"PMLYSpeed\":29.1,\"PMSpeed\":26.8,\"PMSpeedMOM\":0.139}],\"CrossTrafficList\":[{\"AMSpeed\":15.6,\"CrossName\":\"布吉关\",\"PMSpeed\":26.1},{\"AMSpeed\":39.3,\"CrossName\":\"南头关\",\"PMSpeed\":36.3},{\"AMSpeed\":16.8,\"CrossName\":\"梅林关\",\"PMSpeed\":36},{\"AMSpeed\":30,\"CrossName\":\"福龙关\",\"PMSpeed\":14.8},{\"AMSpeed\":29.4,\"CrossName\":\"新清平关\",\"PMSpeed\":53.3},{\"AMSpeed\":24.7,\"CrossName\":\"沙湾关\",\"PMSpeed\":29.3},{\"AMSpeed\":51.3,\"CrossName\":\"新城关\",\"PMSpeed\":39.8}],\"DistrictTrafficList\":[{\"AMLWSpeed\":36.4,\"AMLYSpeed\":37,\"AMSpeed\":36.6,\"AMSpeedMOM\":0.005,\"AMSpeedYOY\":-0.009,\"DistrictName\":\"宝安机场\",\"PMLWSpeed\":30.5,\"PMLYSpeed\":30.1,\"PMSpeed\":29.7,\"PMSpeedMOM\":-0.025,\"PMSpeedYOY\":-0.013},{\"AMLWSpeed\":37.2,\"AMLYSpeed\":38.5,\"AMSpeed\":36.8,\"AMSpeedMOM\":-0.011,\"AMSpeedYOY\":-0.045,\"DistrictName\":\"深圳西站\",\"PMLWSpeed\":34.2,\"PMLYSpeed\":34.2,\"PMSpeed\":34.8,\"PMSpeedMOM\":0.017,\"PMSpeedYOY\":0.017},{\"AMLWSpeed\":26.2,\"AMLYSpeed\":26.7,\"AMSpeed\":25.9,\"AMSpeedMOM\":-0.009,\"AMSpeedYOY\":-0.03,\"DistrictName\":\"深圳站\",\"PMLWSpeed\":21.8,\"PMLYSpeed\":21.4,\"PMSpeed\":23.2,\"PMSpeedMOM\":0.062,\"PMSpeedYOY\":0.082},{\"AMLWSpeed\":26.3,\"AMLYSpeed\":27.4,\"AMSpeed\":26.4,\"AMSpeedMOM\":0.002,\"AMSpeedYOY\":-0.038,\"DistrictName\":\"深圳北站\",\"PMLWSpeed\":27,\"PMLYSpeed\":27.1,\"PMSpeed\":27.1,\"PMSpeedMOM\":0.003,\"PMSpeedYOY\":0},{\"AMLWSpeed\":26.6,\"AMLYSpeed\":29.9,\"AMSpeed\":27.3,\"AMSpeedMOM\":0.027,\"AMSpeedYOY\":-0.088,\"DistrictName\":\"深圳东站\",\"PMLWSpeed\":25.9,\"PMLYSpeed\":27.9,\"PMSpeed\":25.3,\"PMSpeedMOM\":-0.025,\"PMSpeedYOY\":-0.093},{\"AMLWSpeed\":26.2,\"AMLYSpeed\":26.7,\"AMSpeed\":25.9,\"AMSpeedMOM\":-0.009,\"AMSpeedYOY\":-0.03,\"DistrictName\":\"罗湖口岸\",\"PMLWSpeed\":21.8,\"PMLYSpeed\":21.4,\"PMSpeed\":23.2,\"PMSpeedMOM\":0.062,\"PMSpeedYOY\":0.082},{\"AMLWSpeed\":27.2,\"AMLYSpeed\":29.2,\"AMSpeed\":27.9,\"AMSpeedMOM\":0.024,\"AMSpeedYOY\":-0.045,\"DistrictName\":\"福田口岸\",\"PMLWSpeed\":25.8,\"PMLYSpeed\":24.6,\"PMSpeed\":20.2,\"PMSpeedMOM\":-0.215,\"PMSpeedYOY\":-0.178},{\"AMLWSpeed\":34.8,\"AMLYSpeed\":35.1,\"AMSpeed\":34.6,\"AMSpeedMOM\":-0.006,\"AMSpeedYOY\":-0.016,\"DistrictName\":\"皇岗口岸\",\"PMLWSpeed\":27,\"PMLYSpeed\":33.1,\"PMSpeed\":30.6,\"PMSpeedMOM\":0.133,\"PMSpeedYOY\":-0.077},{\"AMLWSpeed\":29.8,\"AMLYSpeed\":31.6,\"AMSpeed\":30,\"AMSpeedMOM\":0.005,\"AMSpeedYOY\":-0.052,\"DistrictName\":\"深圳湾口岸\",\"PMLWSpeed\":27.7,\"PMLYSpeed\":26.9,\"PMSpeed\":27.6,\"PMSpeedMOM\":-0.002,\"PMSpeedYOY\":0.028},{\"AMLWSpeed\":23.2,\"AMLYSpeed\":24.3,\"AMSpeed\":23.6,\"AMSpeedMOM\":0.016,\"AMSpeedYOY\":-0.029,\"DistrictName\":\"东门商业圈\",\"PMLWSpeed\":19.2,\"PMLYSpeed\":21.8,\"PMSpeed\":20.7,\"PMSpeedMOM\":0.075,\"PMSpeedYOY\":-0.05},{\"AMLWSpeed\":24.4,\"AMLYSpeed\":24.3,\"AMSpeed\":24.7,\"AMSpeedMOM\":0.013,\"AMSpeedYOY\":0.018,\"DistrictName\":\"华强北商业圈\",\"PMLWSpeed\":17.6,\"PMLYSpeed\":19,\"PMSpeed\":18.7,\"PMSpeedMOM\":0.064,\"PMSpeedYOY\":-0.018},{\"AMLWSpeed\":25.3,\"AMLYSpeed\":26.2,\"AMSpeed\":26.4,\"AMSpeedMOM\":0.043,\"AMSpeedYOY\":0.006,\"DistrictName\":\"北大医院\",\"PMLWSpeed\":20.5,\"PMLYSpeed\":22.5,\"PMSpeed\":22,\"PMSpeedMOM\":0.074,\"PMSpeedYOY\":-0.023}],\"EndDate\":20171130,\"MainRoadSpeedMOM\":0.02,\"MainRoadSpeedPeak\":33,\"MainRoadSpeedYOY\":0.015,\"PMPeakWholeIndex\":4.2,\"PMPeakWholeSpeed\":29.2,\"PMWholeCross\":33.7,\"PMWholeCrossMOM\":0.043,\"PMWholeCrossYOY\":0.055,\"ParkTrafficList\":[{\"ParkName\":\"福田区\",\"Speed\":17.8,\"SpeedBefore\":0.042,\"SpeedMOM\":0.018},{\"ParkName\":\"罗湖区\",\"Speed\":18.7,\"SpeedBefore\":0.043,\"SpeedMOM\":0.021},{\"ParkName\":\"南山区\",\"Speed\":19.8,\"SpeedBefore\":0.037,\"SpeedMOM\":0.014},{\"ParkName\":\"盐田区\",\"Speed\":22.3,\"SpeedBefore\":0.081,\"SpeedMOM\":0.006}],\"PortSpeed\":27.5,\"PortSpeedMOM\":-0.001,\"PortSpeedYOY\":-0.038,\"SectTrafficList\":[{\"Index\":3.8,\"LWSpeed\":29,\"SectName\":\"福田区\",\"Speed\":30},{\"Index\":4.3,\"LWSpeed\":24.5,\"SectName\":\"罗湖区\",\"Speed\":25.5},{\"Index\":3.6,\"LWSpeed\":29.3,\"SectName\":\"南山区\",\"Speed\":29.6},{\"Index\":2,\"LWSpeed\":34.9,\"SectName\":\"盐田区\",\"Speed\":34.8},{\"Index\":3.2,\"LWSpeed\":33.5,\"SectName\":\"宝安区\",\"Speed\":33.8},{\"Index\":3.2,\"LWSpeed\":32.2,\"SectName\":\"龙岗区\",\"Speed\":32.2},{\"Index\":3.1,\"LWSpeed\":34,\"SectName\":\"光明新区\",\"Speed\":35.7},{\"Index\":2.2,\"LWSpeed\":36.4,\"SectName\":\"坪山区\",\"Speed\":37},{\"Index\":3.6,\"LWSpeed\":30.1,\"SectName\":\"龙华区\",\"Speed\":30.4},{\"Index\":0.9,\"LWSpeed\":49.9,\"SectName\":\"大鹏新区\",\"Speed\":49.7}],\"StartDate\":20171124,\"TotalNo\":235,\"WeeklyNo\":49,\"WholeSpeedPeak\":31,\"WholeSpeedPeakMOM\":0.017,\"WholeSpeedPeakYOY\":-0.017,\"WholeTrafficList\":[{\"SEDate\":\"9.29-10.5\",\"Speed\":36.2,\"SpeedPeak\":28.9,\"WeekNo\":41},{\"SEDate\":\"10.6-10.12\",\"Speed\":38,\"SpeedPeak\":32.9,\"WeekNo\":42},{\"SEDate\":\"10.13-10.19\",\"Speed\":34.6,\"SpeedPeak\":30.7,\"WeekNo\":43},{\"SEDate\":\"10.20-10.26\",\"Speed\":35,\"SpeedPeak\":31.3,\"WeekNo\":44},{\"SEDate\":\"10.27-11.2\",\"Speed\":34.4,\"SpeedPeak\":30.7,\"WeekNo\":45},{\"SEDate\":\"11.3-11.9\",\"Speed\":34.9,\"SpeedPeak\":31.2,\"WeekNo\":46},{\"SEDate\":\"11.10-11.16\",\"Speed\":34.4,\"SpeedPeak\":30.4,\"WeekNo\":47},{\"SEDate\":\"11.17-11.23\",\"Speed\":34.3,\"SpeedPeak\":30.5,\"WeekNo\":48},{\"SEDate\":\"11.24-11.30\",\"Speed\":34.6,\"SpeedPeak\":31,\"WeekNo\":49}]}";
////        saveDB(jsonString);
//        timer();
//    }
}
