package com.wiceflow.designPatterns.abstractFactory;

import com.wiceflow.designPatterns.abstractFactory.impl.Circle;
import com.wiceflow.designPatterns.abstractFactory.impl.Rectangle;
import com.wiceflow.designPatterns.abstractFactory.impl.Square;

/**
 * Created by BF on 2017/12/12.
 */
public class ShapeFactory extends AbstractFactory {

    /**
     * 每个工厂只对应一个生厂商
     * @param color
     * @return
     */
    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getSquare(String square) {
        if (square.equalsIgnoreCase("Circle")){
            return new Circle();
        }else if (square.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }else if (square.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }
}
