package com.wiceflow.json.fastjson;
import com.alibaba.fastjson.JSON;
import com.wiceflow.json.fastjson.entity.User;
import com.wiceflow.json.fastjson.entity.UserGroup;
import com.wiceflow.json.fastjson.entity.iceflow;

/**
 * Created by BF on 2017/12/6.
 * fastJSON 测试
 */
public class fastjsonTest {
    public static void main(String[] args) {
        iceflow iceflow = new iceflow();
        iceflow.setAge(21);
        iceflow.setName("文冰峰");
        iceflow.setSchool("肇庆学院");
        iceflow.setWord("JAVA后端开发");

        User user1 = new User();
        user1.setAge(21);
        user1.setName("文冰峰");
        user1.setDept("后端开发");
        user1.setIceflow(iceflow);

        User user2 = new User();
        user2.setAge(22);
        user2.setName("朱阳升");
        user2.setDept("后端开发组长");

        UserGroup userGroup = new UserGroup();
        userGroup.setName("后端部门");
        userGroup.getList().add(user1);
        userGroup.getList().add(user2);

        String jsonString = JSON.toJSONString(userGroup);

        System.out.println(jsonString);

        UserGroup userGroup1 = JSON.parseObject(jsonString,UserGroup.class);

        System.out.println(userGroup1);
    }
}
