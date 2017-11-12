package com.wiceflow.collection.map;

/**
 * Created by BF on 2017/11/5.
 * 自定义实现Map功能
 * 暂不完美
 */
public class MyMap001 {

    SxtEntry[] arr = new SxtEntry[990];

    int size;

    /**
     * put 加入数据
     * @param key
     * @param value
     */
    public void put(Object key,Object value){
        Boolean flag = containKey(key);
        if (flag){
            for (int i=0;i<size;i++){
                if (arr[i].key.equals(key)){
                    arr[i].value = value;
                    return;
                }
            }
        }
        else {
            SxtEntry e = new SxtEntry(key,value);
            arr[size++] = e;
        }
    }

    public Object get(Object key){
        for (int i=0;i<size;i++){
            if (arr[i].key.equals(key)){
                return arr[i].value;
            }
        }
        return null;
    }

    /**
     * 查找是否存在这个键值
     * @param key
     * @return
     */
    public boolean containKey(Object key){
        for (int i=0;i<size;i++){
            if (arr[i].key.equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找是否存在这个value值
     * @param value
     * @return
     */
    public boolean containValue(Object value){
        for (int i=0;i<size;i++){
            if (arr[i].value.equals(value)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyMap001 a = new MyMap001();
        a.put("冰峰","燕妮");
        a.put("张三","杨幂");
        a.put("李四","单身");
        a.put("张三","单身");
        System.out.println(a.size);

    }

}

class SxtEntry{
    Object key;
    Object value;

    public SxtEntry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
