package com.wiceflow.quote;

/**
 * @author BF
 * @date 2020/5/17
 */
public class Animal {

    private String type;

    private Double height;

    public Animal(String type, Double height) {
        this.type = type;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", height=" + height +
                '}';
    }
}
