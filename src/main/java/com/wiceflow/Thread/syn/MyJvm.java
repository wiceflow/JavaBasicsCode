package com.wiceflow.Thread.syn;
/**
 * 单例创建的方式
 * 1、懒汉式
 * 1)、构造器私有化
 * 2)、声明私有的静态属性
 * 3)、对外提供访问属性的静态方法，确保该对象存在
 *
 * Created by BF on 2017/9/12.
 *
 */
public class MyJvm {
    private static MyJvm instance;
    private MyJvm(){

    }
    public static MyJvm getInstance (){
        if(null==instance){ //提供效率
            synchronized(MyJvm.class){
                if(null==instance){ //安全
                    instance =new MyJvm();
                }
            }
        }
        return instance;
    }


}
/**
 * 饿汉式
 1)、构造器私有化
 * 2)、声明私有的静态属性,同时创建该对象
 * 3)、对外提供访问属性的静态方法
 * Created by BF on 2017/9/12.
 *
 */
class MyJvm2 {
    private static MyJvm2 instance =new MyJvm2();
    private MyJvm2(){

    }
    public static MyJvm2 getInstance (){
        return instance;
    }

}
/**
 * 类在使用的时候加载 ，延缓加载时间
 * Created by BF on 2017/9/12.
 *
 */
class MyJvm3 {
    private static class JVMholder{
        private static MyJvm3 instance =new MyJvm3();
    }
    private MyJvm3(){

    }
    public static MyJvm3 getInstance (){
        return JVMholder.instance;
    }

}