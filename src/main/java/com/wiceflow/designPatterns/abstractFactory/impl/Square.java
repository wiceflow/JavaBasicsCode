package com.wiceflow.designPatterns.abstractFactory.impl;
import com.wiceflow.designPatterns.abstractFactory.Shape;

/**
 * Created by BF on 2017/12/12.
 * 正方形
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
