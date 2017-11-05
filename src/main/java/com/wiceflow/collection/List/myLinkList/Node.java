package com.wiceflow.collection.List.myLinkList;

/**
 * Created by BF on 2017/11/5.
 * 用来表示一个节点
 */
public class Node {
    private Node previous;
    private Object object;
    private Node next;

    public Node() {
    }

    public Node(Node previous, Object object, Node next) {
        this.previous = previous;
        this.object = object;
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
