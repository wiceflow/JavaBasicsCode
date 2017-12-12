package com.wiceflow.designPatterns.abstractFactory.impl;

import com.wiceflow.designPatterns.abstractFactory.Color;

/**
 * Created by BF on 2017/12/12.
 * 蓝色
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
