package com.wiceflow.GenericParadigm.gen01;

/**
 * 泛型类： 声明时使用泛型
 * 字母：
    T type 表示类型
    K V 分别代表键值中的Key Value
    E 代表Element
    ？代表不清楚是什么
    当然也可以不写！！！！
 注意：
    1.泛型只能使用引用类型(String Integer),不能使用基本类型(int)
    2.泛型声明时字母不能使用在  静态属性|静态方法 上  编译时运行检查
 *
 *  @parm <T>
 * Created by BF on 2017/9/19.
 */
public class Student<T1,T2> {
    private T1 javaScore;
    private T2 oracleScore;
    public static void main(String[] args) {

        // 使用时指定类型 (引用类型)
        Student<String,Integer> stu = new Student<String, Integer>();
        // 安全： 赋值的时候自动安全检查
        stu.setJavaScore("BF");
        stu.setOracleScore(80);
        // 省心:  取值的时候不用进行强转
        int num = stu.getOracleScore();
        System.out.println(num);
    }



    public T1 getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(T1 javaScore) {
        this.javaScore = javaScore;
    }

    public T2 getOracleScore() {
        return oracleScore;
    }

    public void setOracleScore(T2 oracleScore) {
        this.oracleScore = oracleScore;
    }


}
