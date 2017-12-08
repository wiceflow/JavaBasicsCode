package com.wiceflow.json.json_write;

/**
 * Created by BF on 2017/12/6.
 */
public class Test {
    public static void main(String[] args) {
        String json1="{ 'desc': 'OK', 'status': 1000, 'data': { 'wendu': '20', 'ganmao': '相对于今天将会出现大幅度降温，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。', 'forecast': [ { 'fengxiang': '北风', 'fengli': '3-4级', 'high': '高温 27℃', 'type': '中雨', 'low': '低温 19℃', 'date': '6日星期五' }, { 'fengxiang': '北风', 'fengli': '微风级', 'high': '高温 23℃', 'type': '大雨', 'low': '低温 17℃', 'date': '7日星期六' }, { 'fengxiang': '北风', 'fengli': '微风级', 'high': '高温 26℃', 'type': '小雨', 'low': '低温 17℃', 'date': '8日星期天' }, { 'fengxiang': '南风', 'fengli': '微风级', 'high': '高温 27℃', 'type': '多云', 'low': '低温 15℃', 'date': '9日星期一' }, { 'fengxiang': '南风', 'fengli': '微风级', 'high': '高温 29℃', 'type': '多云', 'low': '低温 16℃', 'date': '10日星期二' } ], 'yesterday': { 'fl': '微风', 'fx': '北风', 'high': '高温 33℃', 'type': '阴', 'low': '低温 22℃', 'date': '5日星期四' }, 'aqi': '58', 'city': '成都' } }";
        String json = json1.replace("'","\"");

        DefaultJsonParser d = new DefaultJsonParser();
        BaseJsonParser.Entry e = d.parse(json);
        System.out.println(e);
    }
}
