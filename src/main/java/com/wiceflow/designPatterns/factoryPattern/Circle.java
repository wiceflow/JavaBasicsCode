package com.wiceflow.designPatterns.factoryPattern;

/**
 * Created by BF on 2017/12/12.
 */
public class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
