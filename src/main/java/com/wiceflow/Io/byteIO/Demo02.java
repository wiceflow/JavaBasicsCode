package com.wiceflow.Io.byteIO;

import java.io.*;

/**
 * 写出文件
 * 1、建立联系   File对象  目的地
 * 2、选择流     文件输出流  OutputStream FileOutputStream
 * 3、操作  :  write() +flush
 * 4、释放资源 :关闭
 * Created by BF on 2017/9/24.
 */
public class Demo02 {
    public static void main(String[] args) {
        // 1.建立联系 File对象 目的地
        File dest = new File("D:/Jave/test.txt");
        // 2.选择留 文件输出流 OutputStream FileOutputStream
        OutputStream os = null;
        // 以追加形式 写出文件 必须为true 否则为覆盖
        try {
            // 默认为false
            os = new FileOutputStream(dest,true);
            // 3.操作
            String str = "test output";
            // 字符串转字节数组
            byte[] data = str.getBytes();
            os.write(data,0,data.length);
            // 强制刷新出去
            os.flush();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到" + 1111111111);
        } catch (IOException e) {
            System.out.println("文件写出失败" + 222222222);
        }finally {
            // 4.释放资源 ：关闭
            try{
                if (null!=os){
                    os.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流失败" + 333333333);
            }
        }
    }
}
