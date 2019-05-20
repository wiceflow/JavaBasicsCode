package com.wiceflow.polypeptide.impl;

import com.wiceflow.polypeptide.AbstractPeople;

/**
 * @author BF
 * @date 2019/5/17 14:56
 */
public class People extends AbstractPeople {

    @Override
    public void eat() {

    }

    @Override
    public void walk() {

    }

    @Override
    public void speak() {

    }

    public static void main(String[] args) {
        People people = new People();
        people.cry();
        System.out.println("test");
    }
}
