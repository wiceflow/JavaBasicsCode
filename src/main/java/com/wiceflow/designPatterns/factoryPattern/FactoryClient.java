package com.wiceflow.designPatterns.factoryPattern;

/**
 * Created by BF on 2017/12/12.
 * 用户使用，只需传递类型名称
 */
public class FactoryClient {
    public static void main(String[] args) {
        ShapeFactory s = new ShapeFactory();

        Shape s1 = s.getShape("Circle");
        s1.draw();

        Shape s2 = s.getShape("Rectangle");
        s2.draw();
    }
}
