package com.wiceflow.Enum;

/**
 * @author iceflow
 * @date 2018/8/3
 *      枚举类测试
 */
public enum TestEnum {
    /**
     *  枚举中的每一个枚举值相当于枚举类中的一个对象，由于是静态的在编译时会自动new
     *  相当于 A = new TestEnum();
     *        B  = new TestEnum(int i)
     *
     *        num A 中的一个参数
     */
    A,

    /**
     *
     */
    B(2);


    private int num;

    TestEnum(){
        System.out.println("无参测试 测试枚举类：" + num);
    }

    TestEnum(int i) {
        num = i;
        System.out.println("有参测试 测试枚举类：" + num);
    }

    public int getNum(){
        return num;
    }
}
