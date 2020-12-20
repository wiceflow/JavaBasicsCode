package com.wiceflow.designPatterns.abstractFactory;

import com.wiceflow.designPatterns.abstractFactory.impl.Blue;
import com.wiceflow.designPatterns.abstractFactory.impl.Green;
import com.wiceflow.designPatterns.abstractFactory.impl.Red;

/**
 * Created by BF on 2017/12/12.
 * 颜色类工厂
 */
public class ColorFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        if ("red".equalsIgnoreCase(color)){
            return new Red();
        }else if ("green".equalsIgnoreCase(color)){
            return new Green();
        }else if ("blue".equalsIgnoreCase(color)){
            return new Blue();
        }
        return null;
    }

    @Override
    Shape getSquare(String square) {
        return null;
    }
}
