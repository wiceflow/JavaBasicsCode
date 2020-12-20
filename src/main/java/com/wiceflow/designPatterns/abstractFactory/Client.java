package com.wiceflow.designPatterns.abstractFactory;


/**
 * Created by BF on 2017/12/12.
 * 模拟客户
 */
public class Client {
    public static void main(String[] args) {
        // 获得形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shap");
        // 获取圆形对象
        assert shapeFactory != null;
        Shape circle = shapeFactory.getSquare("circle");
        circle.draw();

        // 获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        // 获取蓝色
        assert colorFactory != null;
        Color blue = colorFactory.getColor("blue");
        blue.fill();
    }
}
