package com.wiceflow.designPatterns.factoryPattern;

/**
 * Created by BF on 2017/12/12.
 * 工厂，用于生产给定信息的实体类对象
 */
public class ShapeFactory {
    public Shape getShape(String shapeType){

        Shape shape;
        switch (shapeType){
            case "Circle" :
                shape = new Circle();
                break;
            case "Rectangle" :
                shape = new Rectangle();
                break;
            case "Square" :
                shape = new Square();
                break;
            default:
                shape = null;
        }
        return shape;
    }
}
