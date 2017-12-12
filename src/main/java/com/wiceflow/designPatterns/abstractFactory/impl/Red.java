package com.wiceflow.designPatterns.abstractFactory.impl;

import com.wiceflow.designPatterns.abstractFactory.Color;

/**
 * Created by BF on 2017/12/12.
 * 红色
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
