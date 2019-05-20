package com.wiceflow.polypeptide;

/**
 * @author BF
 * @date 2019/5/17 14:51
 * 测试接口与抽象类的关系  接口
 *
 * <p> 人抽象接口 </p>
 */
public abstract class AbstractPeople implements Animal {

    @Override
    public void cry() {
        System.out.println("我是人，我才不乱叫");
    }

    @Override
    public void fly(){
        System.out.println("我不会飞");
    }
    /**
     * 吃
     */
    @Override
    public abstract void eat();

    /**
     * 走路
     */
    @Override
    public abstract void walk();

    /**
     * 说话交流
     */
    @Override
    public abstract void speak();
}
