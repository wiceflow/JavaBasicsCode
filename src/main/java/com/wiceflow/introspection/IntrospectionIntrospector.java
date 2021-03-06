package com.wiceflow.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BF
 * @date 2018/7/15
 * 内省反射   bean to map
 */
public class IntrospectionIntrospector {
    public static void setPropertyByIntrospector(UserInfo userInfo,String field)throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if(proDescrtptors != null && proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                Class clazz = propDesc.getPropertyType();
                System.out.println(clazz);

                if (clazz.equals(Double.class)){
                    System.out.println("--------------------");
                }


                if(propDesc.getName().equals(field)){
                    Method methodSetUserName = propDesc.getWriteMethod();
                    methodSetUserName.invoke(userInfo, "alan");
                    System.out.println("set userName:"+userInfo.getuserName());
                    break;
                }
            }
        }
    }

    public static void getPropertyByIntrospector(UserInfo userInfo,String field)throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if(proDescrtptors != null && proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                if(propDesc.getName().equals(field)){
                    System.out.println(propDesc.getName());
                    System.out.println(propDesc.getReadMethod().invoke(userInfo));
                    Method methodGetUserName = propDesc.getReadMethod();
                    Object objUserName = methodGetUserName.invoke(userInfo);
                    System.out.println("get userName:"+objUserName.toString());
                    break;
                }
            }
        }
    }

    public  <T> void reflection(T userInfo, Class c) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();
        Map<String,Object> map = new HashMap<>();
        if (fields != null){
            for(Field f : fields){
                String methodName = "get" + f.getName();
                System.out.println(methodName);
                Method method = c.getMethod(methodName);
                method.setAccessible(true);
                map.put(f.getName(),method.invoke(userInfo));
            }
        }

        System.out.println(map.toString());


    }

    public static void main(String[] args) throws Exception {
        IntrospectionIntrospector i = new IntrospectionIntrospector();
        UserInfo userInfo = new UserInfo();
        String field = "userName";
        UserInfo userInfo2 = new UserInfo(1L,"bf",12,"111@ll.com");
        setPropertyByIntrospector(userInfo,field);

//        getPropertyByIntrospector(userInfo,field);

//        i.reflection(userInfo2,UserInfo.class);
    }
}
