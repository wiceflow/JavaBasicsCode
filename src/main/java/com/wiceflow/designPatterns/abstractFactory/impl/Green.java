package com.wiceflow.designPatterns.abstractFactory.impl;

import com.wiceflow.designPatterns.abstractFactory.Color;

/**
 * Created by BF on 2017/12/12.
 * 绿色
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
