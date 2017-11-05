package com.wiceflow.collection.List.myList;

/**
 * Created by BF on 2017/11/4.
 */
public class MyList<E> {
    // List底层存储
    private Object[] value;
    // List已使用大小
    private int size;


    public MyList(){
        // 默认构造方法 设置List大小为10
        this(10);
    }

    public MyList(int size){
        value = new Object[size];
        this.size = 0;
    }


    /**
     * 查找下标数组
     * @param index
     * @return
     */
    public E get(int index){
        // 检查下标是否越界
        lengthCheck(index);
        return (E)value[index];
    }

    /**
     * 数组存储方法
     * @param obj
     */
    public void add(Object obj){
        // 扩容
        if (size>=value.length){
            arrayBig();
        }
        value[size] = obj;
        size++;
    }

    /**
     * 查找数组中是否存在这个对象
     * @param obj
     * @return
     */
    public int search(Object obj){
        if (null==obj){
            return -1;
        }
        else {
            for (int i=0;i<value.length;i++){
                if (value[i]==obj){
                    return i;
                }
            }
            return -1;
        }
    }


    /**
     * 移除操作
     * 实际上是数组的复制
     * @param index
     */
    public void remove(int index){
        lengthCheck(index);
        int length = size - index - 1;
        // 原数组进行替换
        // 判断条件为替换位置不为最后一个
        if (length>0){
            System.arraycopy(value,index,value,index+1,length);
        }
        value[--size] = null;

    }
    /**
     * 扩容方法
     */
    private void arrayBig(){
        int newCapacity = value.length*2+2;
        Object[] newList = new Object[newCapacity];
        // 数组之间的复制
        System.arraycopy(value,0,newList,0,size);
        value = newList;
    }

    /**
     * 判断请求数组是否越界
     * @param index
     */
    private void lengthCheck(int index) {
        // 数组使用大小[0,size-1]
        if (index<0||index>size-1){
            try {
                throw new Exception("数组越界");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对象删除法
     * @param object
     */
    public void remove(Object object){
        if (object==null){
            return;
        }
        for (int i=0;i<size;i++){
            if (value[i].equals(object)){
                remove(i);
            }
        }
    }

    /**\
     * 插入操作，其实也是复制
     * @param index
     * @param obj
     */
    public void add(int index,Object obj) {
        lengthCheck(index);
        // 判断是否满了
        if (index+1>size){
            arrayBig();
        }
        System.arraycopy(value,index,value,index+1,size-index);
        value[index] = obj;
        size++;

    }

    public static void main(String[] args) {
        MyList list = new MyList(2);
        list.add("哈哈哈");
        list.add("真是的");
        list.add("扩容了吗");

        System.out.println(list.size);
        Object a = list.get(1);
        System.out.println(a);

        list.remove(2);
        System.out.println(list.size);
    }
}
