package com.wiceflow.abstruct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BF
 * @date 2018/9/14
 */
public class MapB extends AbstractMap {
    @Override
    public void setAaa(Map<String, String> aaa) {
        Map<String,String> map = new HashMap<>();
        map.put("name","yyn");
        super.setAaa(map);
    }
}
