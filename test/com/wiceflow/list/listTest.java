package com.wiceflow.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iceflow
 * @date 2018/6/21
 */
public class listTest {

    public static void main(String[] args) {
        List<String> a = new ArrayList();

        String  name = null;
        String age = "18";
        String school = "肇庆学院";

        a.add(name);
        a.add(age);
        a.add(school);

        System.out.println(a.toString());

        System.out.println(a.get(0));


    }
}
