package com.wiceflow.GenericParadigm.gen01;

/**
 * 接口中 泛型字母只能使用在方法中,不能使用在全局常量中
 * 接口常量中的全局常量是用static声明的
 * Created by BF on 2017/9/19.
 * @parm <T>
 */
public interface Comparator<T> {
    void compare(T t);
}
