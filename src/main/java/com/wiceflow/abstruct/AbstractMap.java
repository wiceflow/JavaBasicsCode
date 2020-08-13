package com.wiceflow.abstruct;

import java.util.Map;

/**
 * @author BF
 * @date 2018/9/14
 * abstract 的引用是单独引用 并不是单例
 */
public abstract class AbstractMap {
    private Map<String, String> aaa;

    public Map<String, String> getAaa() {
        return aaa;
    }

    public void setAaa(Map<String, String> aaa) {
        this.aaa = aaa;
    }
}
