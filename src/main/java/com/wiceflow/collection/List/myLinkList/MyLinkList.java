package com.wiceflow.collection.List.myLinkList;

/**
 * Created by BF on 2017/11/5.
 * 模拟实现LinkList
 */
public class MyLinkList {
    private Node first;
    private Node last;
    private int size;

    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.add("aaa");
        myLinkList.add("bbb");
        myLinkList.add("ccc");
        myLinkList.add("ddd");

        myLinkList.remove(1);
        myLinkList.add(1,"eee");
        // System.out.println(myLinkList.size());
        // System.out.println(myLinkList.get(2));
        System.out.println(myLinkList.get(1));
    }

    /**
     * 模拟LinkList增加方法
     * @param obj
     */
    public void add(Object obj){
        Node n = new Node();
        n.setObject(obj);
        n.setNext(null);
        // 如果first为空，则赋值，这是后将last也赋值为空
        if (first==null){
            n.setPrevious(null);
            first = n;
            last = n;
        }
        // first 不为空，先将last的下一个节点赋值，然后将last节点替换
        else {
            // 直接往last加
            n.setPrevious(last);
            last.setNext(n);
            last = n;
        }
        size++;
    }

    /**
     * 获取链表为位置中的值
     * @param index
     * @return
     */
    public Object get(int index){
        lengthCheck(index);
        Node n = checkfirst();
        for (int i=0;i<index;i++){
            n = n.getNext();
        }
        return n.getObject();
    }

    /**
     * linkList 移除
     * @param index
     */
    public void remove(int index){

        lengthCheck(index);
        Node n = checkfirst();
        // 先获取要移除的节点
        for (int i=0;i<index;i++){
            n = n.getNext();
        }
        if (n!=null){
            // 获取要移除节点的下一个节点
            Node m = n.getNext();
            // 获取要移除节点的上一个节点
            n = n.getPrevious();
            // 改变节点值
            n.setNext(m);
            size--;
        }
    }

    /**
     * 模拟LinkList插入方法
     * @param index
     * @param obj
     */
    public void add(int index,Object obj){
        lengthCheck(index);
        Node n = checkfirst();
        // 获取要插入节点的上一个对象
        for (int i=0;i<index-1;i++){
            n = n.getNext();
        }
        Node m = new Node();
        m.setObject(obj);
        m.setPrevious(n);
        // 获取下一个对象准备拼接
        Node next = n.getNext();
        // 改变链表方向
        n.setNext(m);
        m.setNext(next);
        size++;
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

    private Node checkfirst(){
        if (first==null){
            try {
                throw new Exception("该数组没有对象");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return first;
    }
    public int size(){
        return size;
    }

}
