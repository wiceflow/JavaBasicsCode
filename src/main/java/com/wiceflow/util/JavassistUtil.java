package com.wiceflow.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.List;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * Javassis工具类
 * 
 * @author: xieqing
 */
public class JavassistUtil extends ClassLoader {
    
    public static String upperFirstChar(String str) {
        char[] s = str.toCharArray();
        // 首字母是大写字母
        if(s[0] >= 97 && s[0] <= 123) {
            s[0] ^= 32;
        }
        return String.valueOf(s);
    }

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, ClassNotFoundException {
        // ClassPool为Student类的容器
        ClassPool pool = ClassPool.getDefault();
        // 通过ClassPool生成一个Organization.java的新类
        CtClass ctClass = pool.makeClass("cn.sibat.sanya.info.center.data.entity.Organization");
        // 模仿从数据库获取到的字段
        List<String> cols = Arrays.asList("orgUuid", "orgName", "orgType", "orgParentUuid");
        // 首先添加类属性private String xxx
        for (String column : cols) {
            // Organization类属性
            CtField orgField = new CtField(pool.getCtClass("java.lang.String"), column, ctClass);
            // 设置类修饰，public或private等
            orgField.setModifiers(Modifier.PRIVATE);
            // 添加属性
            ctClass.addField(orgField);
            // 为属性添加getter和setter方法，column应该使用驼峰命名规则，即单词首字母大写(除第一个外)
            ctClass.addMethod(CtNewMethod.getter("get" + upperFirstChar(column), orgField));
            ctClass.addMethod(CtNewMethod.setter("set" + upperFirstChar(column), orgField));
        }
        // 设置属性默认值
//        ctClass.setAttribute("name", "张三".getBytes());
//        byte[] nameByte = ctClass.getAttribute("name");
        
        //把内存中的对象生成class文件并，也可以不写，注意：不能在输出之后使用ctClass对象，因为内存中已经没有了。
        ctClass.setSuperclass(pool.getCtClass("cn.sibat.model.Pojo"));
        byte[] bytArr = ctClass.toBytecode();
//        Class<?> cls = ctClass.getClass();
        JavassistUtil util = new JavassistUtil();
        Class<?> cls = util.defineClass(null, bytArr, 0, bytArr.length);
        Long uuid = ObjectStreamClass.lookup(cls).getSerialVersionUID();
        System.out.println(uuid);
        FileOutputStream fos = new FileOutputStream(new File("D://Organization.class"));
        fos.write(bytArr);
        fos.close();
    }
    
}
