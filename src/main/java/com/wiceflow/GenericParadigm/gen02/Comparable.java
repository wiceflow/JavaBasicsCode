package com.wiceflow.GenericParadigm.gen02;

/**
 * 泛型接口  与继承同理
 * 重写方法随父类而定
 * Created by BF on 2017/9/20.
 */
public interface Comparable<T> {
    void compare(T t);

}
//声明子类指定具体类型
class Comp implements Comparable<Integer>{

    @Override
    public void compare(Integer t) {
        // TODO Auto-generated method stub
    }

}
//擦除
class Comp1 implements Comparable{

    @Override
    public void compare(Object t) {
        // TODO Auto-generated method stub
    }
}
//父类擦除，子类泛型
class Comp2<T> implements Comparable{

    @Override
    public void compare(Object t) {
        // TODO Auto-generated method stub
    }
}
//子类泛型>=父类泛型
class Comp3<T> implements Comparable<T>{

    @Override
    public void compare(T t) {
        // TODO Auto-generated method stub

    }

}
