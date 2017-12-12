package com.wiceflow.designPatterns.abstractFactory;


/**
 * Created by BF on 2017/12/12.
 * 抽象类工厂
 * 用来获取工厂
 * 先获取总工厂 在获取分工厂下的实现
 */
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getSquare(String square);
}
