package com.wiceflow.GenericParadigm.gen02;

/**
 * 父类为泛型类
 * 1.属性
 *     父类中，随父类而定
 *     子类中，随子类而定
 * 2.方法
 *     随父类而定 --> 参数类型
 * 擦除：
 * 要么同时擦除，要么子类型大于等于父类类型 --> 泛型类型
 * 不能子类擦除了泛型，而父类却还是泛型类
 *
 * Created by BF on 2017/9/20.
 */
public abstract class Father<T1,T2> {
    T1 name;
    public abstract void test(T1 t);
}

/**
 * 子类声明时指定了父类的具体类型
 * 属性类型为具体类型
 * 然后在其对应的构造方法中也要转换成具体的类型
 */
class Child1 extends Father<String,Integer>{

    @Override
    public void test(String s) {

    }
}

/**
 * 子类为泛型，类型在使用的时候确定
 * 子类与父类 泛型使用的符号必须相同，位置可以调换
 * 而重写了父类的方法后，父类方法中使用了那个泛型作为参数，就必须使用相同位置的父类泛型
 * eg：
 *  Father<T1,T2>中有 test<T1 t>
 *      则在其子类Child2继承Father<T4,T3>时候  test方法的参数必须是子类继承父类时父类的第一个泛型变量T4
 * @param <T4>
 * @param <T3>
 */
class Child2<T3,T4> extends Father<T4,T3>{

    @Override
    public void test(T4 t) {

    }
}

/**
 * 子类为泛型，父类不指定类型  -->  称之为泛型的擦除，这是父类的方法总默认使用Object类型代替
 * @param <T1>
 * @param <T2>
 */
class Child3<T1,T2> extends Father{

    @Override
    // 重写父类方法的时候默认使用了Object  不可以修改 方法随父类的类型
    public void test(Object t) {

    }
}

/**
 * 子类和父类同事擦除
 */
class Child4 extends Father{

    @Override
    // 方法随父类，泛型被擦除后转为Object
    public void test(Object t) {

    }
}

/**
 一个错误的方法，子类擦除，而父类却还是泛型
 class Child5 extends Father<T1,T2>{
    @Override

    public void test(Object t) {

    }
 }
 **/