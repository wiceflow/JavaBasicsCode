package com.wiceflow.GenericParadigm.gen02;

/**
 * 泛型的擦除
 * 1、继承|实现声明 不指定类型
 * 2、使用时 不指定类型
 * 统一Object 对待
 * 1、编译器警告 消除使用Object
 * 2、不完全等同于Object ,编译不会类型检查
 * Created by BF on 2017/9/20.
 */
public class Student<T> {
    private T javaScore;
    private T oracleScore;

    //泛型声明时不能使用 静态属性|静态方法上
    //private static T1 test;


    public T getJavaScore() {
        return javaScore;
    }


    public void setJavaScore(T javaScore) {
        this.javaScore = javaScore;
    }


    public T getOracleScore() {
        return oracleScore;
    }


    public void setOracleScore(T oracleScore) {
        this.oracleScore = oracleScore;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Student stu1 = new Student();
        //消除警告 使用 Object
        Student<Object> stu = new Student<Object>();

        // 编译器警告  jdk 1.8没有警告了
        Student stu3 = new Student();

        //stu.setJavaScore("af"); //以Object对待


        //擦除，不会类型检查 这时候stu1 是泛型 test是Integer
        test(stu1); //stu1 相当于Object 但是不完全等同Object
        //test(stu);
        test1(stu1);
        test1(stu);

    }

    public static void test(Student<Integer> a) {

    }

    public static void test1(Student<?> a) {

    }
}
