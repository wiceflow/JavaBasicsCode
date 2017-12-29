package com.wiceflow.collection.array;

/**
 * Created by BF on 2017/12/25.
 * 测试继承关系
 */
public class Manager extends Employee {
    @Override
    public double getPrice() {
        double price = super.getPrice();
        return price+1000;
    }
}
