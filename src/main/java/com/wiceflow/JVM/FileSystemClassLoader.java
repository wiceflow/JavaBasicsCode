package com.wiceflow.JVM;

import java.io.*;

/**
 * 自定义文件系统类加载器
 * Created by BF on 2017/9/18.
 */
public class FileSystemClassLoader extends ClassLoader {
    // 传进一个目录
    // d://myjava/com/wiceflow/JVM/demo01.class   -->  com.wiceflow.JVM.demo01

    // 指定根目录
    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }
    // 重写父类方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class<?> c = findLoadedClass(name);
        // 应该先查询有没有加载过这个类。如果有加载过，则直接返回加载好的类，如果没有，则加载新的内容
        if (c!=null){
            return c;
        }else {
            // 交给父类去加载，获得父类加载器
            // 因为自定义加载器的上一层次是app类加载器，所以这里获得到的应用程序类加载器appliaction class loader
            try{
                // 委托给父类加载器 这时候进入系统的加载器中，会一层一层的往上送
                ClassLoader parent = this.getParent();
                c = parent.loadClass(name);
            }catch (Exception e){
                System.out.println("父级异常什么都不做！");
            }
            if (c!=null){
                return c;
            }else{
                // 自定义IO流读取
                byte[] classData =  getClassData(name);
                if (classData == null){
                    // 如果还是找不到，就手动抛出异常
                    throw new ClassNotFoundException();
                }else{
                    c = defineClass(name,classData,0,classData.length);
                    return c;
                }
            }
        }
    }

    /**
     * 自定义字节流读取器
     * @param name com.wiceflow.JVM.demo01 -->  d://myjava/com/wiceflow/JVM/demo01.class
     * @return
     */
    private byte[] getClassData(String name){
        // 转换路径
        String path = rootDir + "/" + name.replace('.','/') + ".class";
        // 字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 文件流
        InputStream is = null;
        try {
            is = new FileInputStream(path);

            byte[] buffer = new byte[1024];
            int temp = 0;
            while ((temp=is.read(buffer))!=-1){
                baos.write(buffer,0,temp);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
