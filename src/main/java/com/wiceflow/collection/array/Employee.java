package com.wiceflow.collection.array;

/**
 * Created by BF on 2017/12/25.
 * 测试继承关系
 */
public class Employee {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
