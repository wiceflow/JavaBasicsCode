package com.wiceflow.Sort.Comparator;

/**
 * Created by BF on 2017/9/19.
 */
class Base{
    public Base(String s){
        System.out.print("B");
    }
}
public class Derived extends Base{
    public Derived (String s) {
        super("2");
        System.out.print("D");
    }
    public static void main(String[] args){
        String  first = "a ";
        new Derived("C");
    }
}