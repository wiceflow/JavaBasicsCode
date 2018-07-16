package com.wiceflow.introspection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author BF
 * @date 2018/7/15
 */
public class IntrospectionPropertyDescriptor {


    public static void setProperty(UserInfo userInfo,String field)throws Exception{
        PropertyDescriptor propDesc = new PropertyDescriptor(field,UserInfo.class);
        Method methodSetUserName = propDesc.getWriteMethod();
        methodSetUserName.invoke(userInfo, "wong");
        //System.out.println("set userName:"+userInfo.getUserName());
    }


    public static void getProperty(UserInfo userInfo,String userName)throws Exception{
        PropertyDescriptor proDescriptor = new PropertyDescriptor(userName,UserInfo.class);
        Method methodGetUserName = proDescriptor.getReadMethod();
        Object objUserName = methodGetUserName.invoke(userInfo);
        System.out.println("get userName:"+objUserName.toString());
    }

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo();
        String field = "userName";

        setProperty(userInfo,field);
    }
}
