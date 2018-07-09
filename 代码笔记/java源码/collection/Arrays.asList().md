# 浅谈Arrays.asList()方法的使用  
首先，该方法是将数组转化为list。有以下几点需要注意：

* （1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）

* （2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新

* （3）不支持add和remove方法

上代码
```java
package com.shanheyongmu.test;

import java.util.Arrays;
import java.util.List;

abstract public class AsllistTest {

    public static void main(String[] args) {
        String[] s = {"aa","bb","cc"};
        List<String> strlist = Arrays.asList(s);
        for(String str:strlist){
            System.out.println(str);
        }
        System.out.println("------------------------");
        //基本数据类型结果打印为一个元素
        int[] i ={11,22,33};
        List intlist = Arrays.asList(i);
        for(Object o:intlist){
            System.out.println(o.toString());
        }
        System.out.println("------------------------");
        Integer[] ob = {11,22,33};
        List<Integer> oblist = Arrays.asList(ob);
        for(int a:oblist){
            System.out.println(a);
        }
        System.out.println("------------------------");
    }
}
```
运行结果  
```
aa
bb
cc
----------------
[I@2524e205
---------分割线----------
22
--------------------
```
CSDN另外一篇关于asList代码如下   
```java
package com.shanheyongmu;

import java.util.Arrays;
import java.util.List;

/**
 *  
 * 本类演示了Arrays类中的asList方法
 * 通过四个段落来演示,体现出了该方法的相关特性.
 *  
 * (1) 该方法对于基本数据类型的数组支持并不好,当数组是基本数据类型时不建议使用
 * (2) 当使用asList()方法时，数组就和列表链接在一起了.
 *     当更新其中之一时，另一个将自动获得更新。
 *     注意:仅仅针对对象数组类型,基本数据类型数组不具备该特性
 * (3) asList得到的数组是的没有add和remove方法的
 *  
 * 阅读相关:通过查看Arrays类的源码可以知道,asList返回的List是Array中的实现的
 * 内部类,而该类并没有定义add和remove方法.另外,为什么修改其中一个,另一个也自动
 * 获得更新了,因为asList获得List实际引用的就是数组
 */
public class AsListTest {
    public static void main(String[] args) {

        /*段落一:基本数据类型使用asList的问题   */

        /* 说明:虽然在jdk1.6中能够将基本数据类型的数组转换成List,但还是有个缺陷   */
        System.out.println("段落一开始分割线--------------");
        int [] a_int = { 1, 2, 3, 4 };
        /*预期输出应该是在1，2，3，4，但实际上输出的仅仅是一个引用,这里它把a_int 当成一个元素*/
        List a_int_List = Arrays.asList(a_int);
        foreach(a_int_List);
        /*为此我们需要这样遍历其中元素*/
        foreachForBase(a_int_List);

        /*段落二：对象类型的数组使用asList，是我们预期的 */
        System.out.println("段落二开始分割线--------------");
        Integer[] a_Integer= new Integer[] { 1, 2, 3, 4 };
        List a_Integer_List = Arrays.asList(a_Integer);
        foreach(a_Integer_List);


        /*段落三:当更新数组或者asList之后的List,另一个将自动获得更新*/
        System.out.println("段落三开始分割线--------------");
        a_Integer_List.set(0, 0);
        foreach(a_Integer_List);
        foreach(a_Integer);

        a_Integer[0] =5;
        foreach(a_Integer_List);
        foreach(a_Integer);


        /*段落四:对基本类型数组，通过asList之后的List修改对应的值后,在运行时会报出异常
         * 但是基本类型数组对应的List是会发生变化的，这是毫无疑问的
         *
         *
         * a_int_List.set(0,0);
         * foreach(a_int_List);foreach(a_int);
         * */

        System.out.println("段落四开始分割线------------------");
        a_int[0]=5;
        foreachForBase(a_int_List);
        foreach(a_int);

    }

    /*打印方法*/
    private static void foreach(List list) {
        for (Object object : list) {
            System.out.println(object + " ");
        }
        System.out.println();

    }


    private static void foreachForBase(List a_int_List) {
        int[] _a_int = (int[]) a_int_List.get(0);    
        foreach(_a_int);
    }

    private static void foreach(int[] a_int) {
        for (int i : a_int) {
            System.out.println(i + " ");
        }
        System.out.println();
    }


    private static void foreach(Integer[] _a_Integer) {
        for (int i : _a_Integer) {
            System.out.println(i + " ");
        }
        System.out.println();
    }
}
```  

转自  
http://blog.csdn.net/cntanghai/article/details/7188296
http://www.cnblogs.com/Miracle-Maker/p/6360069.html
