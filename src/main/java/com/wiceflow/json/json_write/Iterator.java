package com.wiceflow.json.json_write;
/**
 * 迭代器
 * @author BF
 * @param <T> 泛型
 */
public class Iterator<T> {

    /** 数据源 */
    private T[] array;
    
    /** 数据源长度 */
    private int length = -1;
    
    /** 游标,当前遍历下标 */
    private int cursor;
    
    public Iterator(T[] array) {
        this.array = array;
        this.length = array.length;
        this.cursor = 0;
    }
    
    /**
     * 是否还有下一个元素
     * @return
     */
    public boolean hasNext() {
        return this.cursor + 1 < this.length ? true : false;
    }
    
    /**
     * 数组已经遍历结束
     * @return
     */
    public boolean isEnd() {
        return !this.isNotEnd();
    }
    
    /**
     * 数组还没有遍历结束
     * @return
     */
    public boolean isNotEnd() {
        return this.cursor < this.length ? true : false;
    }
    
    /**
     * 是否还有上一个元素
     * @return
     */
    public boolean hasLast() {
        return this.cursor - 1 >= 0 ? true : false;
    }
    
    /**
     * 返回当前遍历元素
     * @return
     */
    public T getElement() {
        return this.array[this.cursor];
    }
    
    /**
     * 返回指定下标元素
     * @param cursor [int]指定下标
     * @return
     */
    public T getElement(int cursor) {
        if (cursor < 0 || cursor >= this.array.length) {
            throw new IllegalArgumentException("传入的游标范围不合法!cursor = " + cursor);
        } else {
            return this.array[cursor];
        }
    }
    
    /**
     * 单纯的就看一下下一个元素,不改变游标
     * @return
     */
    public T nextElementLast() {
        return this.array[this.cursor + 1];
    }
    
    /**
     * 游标向后移动,并返回元素
     * @return
     */
    public T nextElement() {
        return this.array[++ this.cursor];
    }
    
    /**
     * 返回当前元素,然后游标向后移动
     * @return
     */
    public T elementNext() {
        return this.array[this.cursor ++];
    }
    
    /**
     * 游标向前移动,并返回元素
     * @return
     */
    public T lastElement() {
        return this.array[-- this.cursor];
    }
    
    /**
     * 单纯的就看一下上一个元素,不改变游标
     * @return
     */
    public T lastElementNext() {
        return this.array[this.cursor - 1];
    }
    
    /**
     * 返回当前元素,然后游标向前移动
     * @return
     */
    public T elementLast() {
        return this.array[this.cursor --];
    }
    
    /**
     * 下标移动到下一位
     */
    public void next() {
        this.cursor ++;
    }
    
    /**
     * 下标移动到上一位
     */
    public void last() {
        this.cursor --;
    }
    
    /**
     * 当前游标
     * @return
     */
    public int getCursor() {
        return this.cursor;
    }
    
    /**
     * 返回迭代器中数据数组长度
     * @return
     */
    public int length() {
        return this.array.length;
    }
    
}
