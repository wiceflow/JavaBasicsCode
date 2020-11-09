package com.wiceflow.Reflection;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author BF
 * @date 2018/11/1
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName("com.wiceflow.Reflection.pojo.User");
        Object[] o = new Object[]{1,2,null};
        Field[] fields = c.getDeclaredFields();
        Class[] c2 = new Class[fields.length];
        for (int i=0;i<fields.length;i++){
            c2[i] = fields[i].getType();
        }

        Constructor constructor = c.getConstructor(c2);
        constructor.newInstance(o);
    }
}
