package com.wiceflow.quote;

/**
 * @author BF
 * @date 2020/5/19
 *
 * 强引用测试
 */

public class StongQuote {

    /**
     * 这个例子可以看到  animal2 是强引用到了 new Animal() 这个对象了，当 animal1 = null 时候并不会发生改变
     *
     * 当一个对象指向另一个对象的时候，它也就指向了那个对象的引用
     *
     *  A -> B 当 B 修改时候 A 也会修改，此时他们是指向同一个引用对象
     *  <p> 但是当 B = null 的时候， A 还是指向原来的引用对象， 这个不会改变 </p>
     *
     * @param args [String数组]
     */
    public static void main(String[] args) {
        Animal animal1 = new Animal("狮子",156.0);

        Animal animal2 = animal1;
        // Animal{type='狮子', height=156.0}
        System.out.println(animal2.toString());

        animal1.setType("老虎");
        // Animal{type='老虎', height=156.0}
        System.out.println(animal2.toString());

        animal1 = null;
        // Animal{type='老虎', height=156.0}
        System.out.println(animal2.toString());
    }
}
