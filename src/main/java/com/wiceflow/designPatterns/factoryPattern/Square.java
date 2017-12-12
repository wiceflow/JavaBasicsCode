package com.wiceflow.designPatterns.factoryPattern;

/**
 * Created by BF on 2017/12/12.
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
