package com.wiceflow.designPatterns.abstractFactory;

/**
 * Created by BF on 2017/12/12.
 * 工厂创造器/工厂生成器
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if (choice.equalsIgnoreCase("shape")){
            return new ShapeFactory();
        }else if (choice.equalsIgnoreCase("color")){
            return new ColorFactory();
        }
        return null;
    }
}
