package com.wiceflow.Io.byteIO;

import java.io.*;

/**
 * 文件的读取
 * 1、建立联系   File对象
 * 2、选择流     文件输入流  InputStream FileInputStream
 * 3、操作  : byte[] car =new byte[1024];  +read+读取大小
 * 输出
 * 4、释放资源 :关闭
 * Created by BF on 2017/9/24.
 */
public class Demo01 {
    public static void main(String[] args) {
        // 1.建立联系 File对象
        File src = new File("D:/java/test.txt");
        System.out.println(src.getName());
        // 2.选择流
        InputStream is = null;
        try {
            is = new FileInputStream(src);
            // 3.操作 不断读取 缓冲数组
            byte[] car = new byte[1024];
            // 4.接收 实际读取大小
            int len = 0;
            StringBuilder sb = new StringBuilder();
            while ((len=is.read())!=-1){
                String info = new String(car,0,len);
                sb.append(info);
            }
            System.out.println(sb.toString());
        }catch (FileNotFoundException e){
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        }finally {
            try {
                // 5.释放资源
                if (null != is){
                    is.close();
                }
            } catch (IOException e) {
                System.out.println("关闭文件输入流失败");
            }
        }
    }
}
