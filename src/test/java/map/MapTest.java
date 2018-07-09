package map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

    /**
     * map 赋值到list属于引用传递，不可用clear清除对象信息
     */
    @Test
    public void mapClearTest(){
        Map<String,String> map1 = new HashMap();
        List<Map> list = new ArrayList<>();

        map1.put("name","bf");
        map1.put("age","23");
        map1.put("phone","110");

        list.add(map1);
        // [{phone=110, name=bf, age=23}]
        System.out.println(list.toString());

        map1.put("age","22");
        System.out.println(list.toString());

        map1.clear();
        // [{}]
        System.out.println(list.toString());
    }

    @Test
    public void JSONObjeactClearTest(){
        JSONObject map1 = new JSONObject();
        JSONArray l = new JSONArray();

        map1.put("name","bf");
        map1.put("age","23");
        map1.put("phone","110");

        l.add(map1);
        // [{"phone":"110","name":"bf","age":"23"}]
        System.out.println(JSONArray.toJSONString(l));

        map1.clear();
        // [{}]
        System.out.println(JSONArray.toJSONString(l));
    }

    /**
     * 证明方法调用才会出现判断是否为空
     */
    @Test
    public void mapIsNullTest(){
        Map<String,Object> map = new HashMap();
        map.put("name","iceflow");
        map.put("age",22);

        String name = map.get("name").toString();
        System.out.println(name);

        // actual map,get("wife") is null
        Object wife = map.get("wife");
        // 这里会发生 java.lang.NullPointerException
        System.out.println(wife.toString());

        // 这里会发生 java.lang.NullPointerException
        String phone = map.get("phone").toString();
        System.out.println(phone);
    }
}
