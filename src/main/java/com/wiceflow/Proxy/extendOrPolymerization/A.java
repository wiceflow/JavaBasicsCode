package com.wiceflow.Proxy.extendOrPolymerization;

import java.io.File;

/**
 * 一个已存在的类
 * （模拟代理）
 * Created by BF on 2017/10/12.
 */
public class A implements Sayable {
    public void say() throws InterruptedException {
        System.out.println("我是已经存在的了，无法修改的。");
        // 包含包名
        File f = new File(this.getClass().getResource("").getPath());
        // 不包含包名
        File f2 = new File(this.getClass().getResource("/").getPath());
        String s1 = f.getAbsolutePath();
        String s2 = f2.getAbsolutePath();

        System.out.println(s1);
        System.out.println(s2);

        // 尝试截取包名
        String s3 = s1.substring(s2.length()+1,s1.length());
        s3 = s3.replace("\\",".");
        System.out.println(s3);


        //String s = this.getClass().getSimpleName();
        //System.out.println(s);

    }
}
