package com.wiceflow.Proxy.dynamicAgen;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 模拟JDK的动态代理
 * JDK 动态代理类就叫 Proxy
 * 动态代理对私有方法无法代理？
 * Created by BF on 2017/10/12.
 */
public class Proxy {
    /**
     * JDK中动态代理的方法名就叫 newProxyInstance
     * @return 返回一个成功代理对象
     */
    public static Object newProxyInstance(Class infce,InvocationHandler h){
        System.out.println(infce);
        // 定义换行符 下面会频繁用到
        String rt = "\r\t";
        // 字符串拼接
        // TODO 尝试用StingBuffer
        String methodStr = "";
        // 反射获取传进来的接口所有方法
        // 为什么不使用 getDeclaredMethods() ,因为接口中方法都是全局的，不存在私有方法
        Method[] methods = infce.getMethods();
        System.out.println(infce.getSimpleName());
        // 定义动态生成java文件的路径
        // 全路径
        String root1 = new File(infce.getResource("").getPath()).getAbsolutePath();
        // 无包名路径
        String root2 = new File(infce.getResource("/").getPath()).getAbsolutePath();
        String pack = root1.substring(root2.length()+1,root1.length());
        // 截取包名
        pack = pack.replace("\\",".");
        System.out.println("动态生成的文件路劲为:" + root1);
        System.out.println("动态生成的文件包名为:" + pack);
        // 类名
        String className = infce.getSimpleName() + "Proxy";
        System.out.println("动态生成的文件类名为:" + className);

        //当前类的包名 在动态生成的文件中需要用到
        String r1 = new File(Proxy.class.getResource("").getPath()).getAbsolutePath();
        String r2 = new File(Proxy.class.getResource("/").getPath()).getAbsolutePath();
        String r3 = r1.substring(r2.length()+1,r1.length());
        r3 = r3.replace("\\",".");
        System.out.println("当前类的包名："+r3);

        for(Method m : methods) {
            methodStr +=
                    "@Override" + rt +
                    "public void " + m.getName() + "() {" + rt +
                    "    try {" + rt +
                    "       Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
                    "       h.invoke(this, md);" + rt +
                    "    }catch(Exception e){" + rt +
                    "       e.printStackTrace();" + rt +
                    "       System.out.println(\"动态生成的方法出错\");" + rt +
                    "    }" + rt +
                    "}" + rt;
        }

        String src =
                "package " + pack + ";" + rt +
                "import java.lang.reflect.Method;" + rt +
                // JDK中类名为 $Proxy1
                "public class "+ className +" implements " + infce.getName() + "{" + rt +
                 r3 + ".InvocationHandler h;" + rt +
                "    public " + className + " (InvocationHandler h) {" + rt +
                "        this.h = h;" + rt +
                "    }" + rt +
                    methodStr +
                "}";
        // 全路径 + 类名 这时候路径是本来就存在的 所有可以直接创建
        String fileName = root1 + "\\" + className + ".java";
        System.out.println("当前生成文件路径名：" + fileName);
        File f = new File(fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(src);
            fw.flush();  // 强制刷新
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Java内置动态编译类 从JDK1.4开始添加的
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        try {
            fileMgr.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("动态编译失败");
        }
        //load into memory and create an instance
        // 特殊的ClassLoader 指定路径加载class
        // 任何目录都可以加载class文件字节码 都可以利用反射
        URL[] urls = new URL[0];
        try {
            // 反射加载出这个类 url访问格式  本地文件固定格式 file:/
            urls = new URL[] {new URL("file:/" + root2)};
            URLClassLoader ul = new URLClassLoader(urls);
            // 这个类的二进制码对象
            Class c = ul.loadClass(pack + "." + className);
            // 生成某个类的具体构造方法
            Constructor ctr = c.getConstructor(InvocationHandler.class);
            Object m = ctr.newInstance(h);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
